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

import static java.util.Collections.singletonList;


@Service
public class TinyMStreamClient {

    /**
     * Values that are declared on resources in application.properties.
     * @value email is a email address value that is used to connect TinyMesh cloud
     * @value pass is a password value that is used to connect TinyMesh cloud
     * @value baseURL is a url address of TinyMesh cloud

     */
    @Value("${tinymesh.client.email}")
    private String email;

    @Value("${tinymesh.client.pass}")
    private String pass;

    @Value("${tinymesh.client.base_url}")
    private String baseURL;


    private WebClient webClient;
    private ObjectMapper objectMapper;
    private DeviceRepository deviceRepo;

    /**
     * Constructor which takes {@link WebClient} and {@link DeviceRepository}
     * @param webClient is an interface representing the main entry point for performing web requests
     * @param deviceRepo is a repository for saving Devices.
     */
    public TinyMStreamClient(WebClient webClient, DeviceRepository deviceRepo) {
        this.webClient = webClient;
        this.deviceRepo = deviceRepo;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Method connects to Tiny Mesh cloud using parameters and streams device data from API.
     *
     * Streaming data from Tiny Mesh Cloud is done over HTTP using GET /v2/messages/T.
     * @param email is a email address value that is used to connect TinyMesh cloud
     * @param pass is a password value that is used to connect TinyMesh cloud
     * @return result
     * @see Flux
     */
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

    /**
     * It fetches Device updates.
     */
    public void streamDeviceUpdates() {
        streamMessages(email, pass).subscribe(this::updateDeviceState, Throwable::printStackTrace);
    }

    /**
     * Method updates data in {@link DeviceRepository} (DB).
     *
     * {@link DoorSensor} data is saved in repository!
     * @param deviceProps is deviceProperties
     */
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
            deviceRepo.save(device);
        }
    }
}