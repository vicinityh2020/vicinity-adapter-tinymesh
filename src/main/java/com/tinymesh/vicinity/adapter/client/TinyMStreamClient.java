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


@Service
public class TinyMStreamClient {
    @Value("${tinymesh.client.email}")
    private String email;

    @Value("${tinymesh.client.pass}")
    private String pass;


    private WebClient webClient;
    ObjectMapper objectMapper;
    private DeviceRepository deviceRepo;

    public TinyMStreamClient(WebClient webClient, DeviceRepository deviceRepo) {
        this.webClient = webClient;
        this.deviceRepo = deviceRepo;
        this.objectMapper = new ObjectMapper();
    }

    public Flux<String> streamMessages(String email, String pass) {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        final String endpoint = "/v2/messages/T";

        map.put("date.from", Collections.singletonList("NOW//-5MINUTE"));
        map.put("data.encoding", Collections.singletonList("hex"));
        map.put("continuous", Collections.singletonList("true"));
        map.put("stream", Collections.singletonList("true"));

        UriComponents uri = UriComponentsBuilder.fromUriString(endpoint).queryParams(map).buildAndExpand();

        return webClient.get()
                .uri(uri.toString())
                .header("Authorization", "Basic " + Base64Utils
                        .encodeToString((email + ":" + pass).getBytes(Charset.forName("US-ASCII"))))
                .retrieve()
                .bodyToFlux(String.class);
    }

    public void printStreamedMessages() {
        streamMessages(email,pass).subscribe(deviceProps -> {
            DoorSensor door = null;
            try {
                door = objectMapper.readValue(deviceProps, DoorSensor.class);
            } catch (IOException e) {
                System.out.println("\n=========\nRecieved META\n=========\n");
            }
            if (door != null){
                System.out.println(deviceProps);
                Device device = deviceRepo.findByTinyMuid(door.getProtoTm().getUid());

                if (door.getProtoTm().getDio().getGpio5() == 1) {
                    device.setState(true);
                } else {
                    device.setState(false);
                }
                deviceRepo.save(device);
                System.out.println();
            }

        }, Throwable::printStackTrace);
    }
}