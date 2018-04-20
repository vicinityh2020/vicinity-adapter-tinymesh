package com.tinymesh.vicinity.adapter.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinymesh.vicinity.adapter.entity.Device;
import com.tinymesh.vicinity.adapter.model.streamingModels.DoorSensor;
import com.tinymesh.vicinity.adapter.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;

import static java.util.Collections.singletonList;

@Service
public class TinyMStreamClient {
    @Value("${tinymesh.client.email}")
    private String email;

    @Value("${tinymesh.client.pass}")
    private String pass;

    private WebClient webClient;
    private ObjectMapper objectMapper;
    private DeviceRepository deviceRepo;

    public TinyMStreamClient(WebClient webClient, DeviceRepository deviceRepo) {
        this.webClient = webClient;
        this.deviceRepo = deviceRepo;
        this.objectMapper = new ObjectMapper();
    }

    public Flux<String> streamMessages(String email, String pass) {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        final String endpoint = "/v2/messages/T";

        map.put("date.from", singletonList("NOW//-5MINUTE"));
        map.put("data.encoding", singletonList("hex"));
        map.put("continuous", singletonList("true"));
        map.put("stream", singletonList("true"));

        UriComponents uri = UriComponentsBuilder.fromUriString(endpoint).queryParams(map).buildAndExpand();

        Flux<String> result = webClient.get()
                .uri(uri.toString())
                .header("Authorization", "Basic " + Base64Utils
                        .encodeToString((email + ":" + pass).getBytes(Charset.forName("US-ASCII"))))
                .retrieve()
                .bodyToFlux(String.class);

        return result;
    }

    public void streamDeviceUpdates() {
        streamMessages(email, pass).subscribe(this::updateDeviceState, Throwable::printStackTrace);
    }

    public void updateDeviceState(String deviceProps) {
        DoorSensor door = null;
        try {
            door = objectMapper.readValue(deviceProps, DoorSensor.class);
        } catch (IOException e) {
        }
        if (door != null) {
            Device device = deviceRepo.findByTinyMuid(door.getProtoTm().getUid());

            if (door.getProtoTm().getDio().getGpio5() == 1) {
                device.updateDeviceState(true, door.getDatetime());
            } else {
                device.updateDeviceState(false, door.getDatetime());
            }
            System.out.println(device);
            deviceRepo.save(device);
        }
    }
}
