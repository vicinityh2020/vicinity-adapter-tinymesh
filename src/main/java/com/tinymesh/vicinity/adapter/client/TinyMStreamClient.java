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
import java.util.List;
import java.util.stream.Collectors;

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

    @Value("${tinymesh.networkID}")
    private String networkID;

    private WebClient webClient;
    private ObjectMapper objectMapper;
    private DeviceRepository deviceRepo;
    private TinyMClient tinyMClient;

    /**
     * Constructor which takes {@link WebClient} and {@link DeviceRepository}
     * @param webClient is an interface representing the main entry point for performing web requests
     * @param deviceRepo is a repository for saving Devices.
     */
    public TinyMStreamClient(WebClient webClient, DeviceRepository deviceRepo, TinyMClient tinyMClient) {
        this.webClient = webClient;
        this.deviceRepo = deviceRepo;
        this.objectMapper = new ObjectMapper();
        this.tinyMClient = tinyMClient;
    }

    /**
     * Method connects to Tiny Mesh cloud using parameters and streams device data from API.
     * Streaming data from Tiny Mesh Cloud is done over HTTP using GET /v2/messages/T.
     * @param email is a email address value that is used to connect TinyMesh cloud
     * @param pass is a password value that is used to connect TinyMesh cloud
     * @return result
     * @see Flux
     */
    public Flux<String> streamMessages(String email, String pass) {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        map.put("date.from", singletonList("NOW//-5MINUTE"));
        map.put("data.encoding", singletonList("hex"));
        map.put("continuous", singletonList("true"));
        map.put("stream", singletonList("true"));

        UriComponents uri = UriComponentsBuilder.fromUriString("/v2/messages/{networkID}").queryParams(map).buildAndExpand(networkID);

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
            return;
        }
        if (door != null) {
            Long eventDeviceUID = door.getProtoTm().getUid();
            Device device = deviceRepo.findByTinyMuid(eventDeviceUID);
            //from stream client
            if(device == null){ // need to resync devices
                reSyncDevices(door, eventDeviceUID);
            }else{ // we know the device, just update state and datetime
                updateDeviceFromDoorData(door, device);
                deviceRepo.save(device);
            }
        }
    }

    private void reSyncDevices(DoorSensor door, Long eventDeviceUID) {
        List<Device> devices = tinyMClient.syncDevices();
        List<Device> unsynced_devices = devices.stream()
                .filter(d ->
                        d.getTinyMuid() == door.getProtoTm().getUid())
                .collect(Collectors.toList());
        if (unsynced_devices.size() == 0) {
            return;
        }else {
            for (Device d: unsynced_devices){
                if(d.getTinyMuid() == eventDeviceUID){
                    updateDeviceFromDoorData(door, d);
                }
            }
            deviceRepo.saveAll(unsynced_devices);
        }
    }

    private void updateDeviceFromDoorData(DoorSensor door, Device device) {
        if (door.getProtoTm().getDio().getGpio5() == 1) {
            device.updateDeviceState(true, door.getDatetime());
        } else {
            device.updateDeviceState(false, door.getDatetime());
        }
    }
}