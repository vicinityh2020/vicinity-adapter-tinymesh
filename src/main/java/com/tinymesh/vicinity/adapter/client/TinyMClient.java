package com.tinymesh.vicinity.adapter.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinymesh.vicinity.adapter.entity.Device;
import com.tinymesh.vicinity.adapter.model.DoorSensorJSON;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpMethod.GET;

@Service
public class TinyMClient {
    private RestTemplate restTemplate;

    @Value("${tinymesh.client.email}")
    private String email;
    @Value("${tinymesh.client.pass}")
    private String pass;
    @Value("${tinymesh.client.base_url}")
    private String baseURL;
    @Value("${tinymesh.networkID}")
    private String networkID;

    public TinyMClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Recieves a list of devices from TinyM cloud and maps them to a list of Device entities ready to be saved to DB.
     *
     * @return List of Device
     */
    public List<Device> syncDevices() {
        final String endpointDevice = "v2/device";
        List<DoorSensorJSON> devices = this.requestDevices();
        List<Device> deviceObjects = new ArrayList<>();


        //filter out devices that are not provisioned as per description in T330
        //and creates Device objects suitable for DB
        devices.stream().filter(device -> device.getType().equals("door-sensor") //&&
                            /*!device.getType().equals("gateway") &&
                            !device.getType().contains("building-sensor")*/)
                        .forEach(device -> {
                            final String deviceURL = String.format("%s/%s/%s/%s",
                                    baseURL, endpointDevice,
                                    device.getNetwork(),
                                    device.getKey());

                            deviceObjects.add(new Device(
                                    device.getName(),
                                    device.getType(),
                                    UUID.randomUUID(),
                                    // save time in utc to avoid any errors caused by time zone mismatch
                                    LocalDateTime.now(ZoneId.of("UTC")),
                                    true, deviceURL,
                                    device.getAddress(),
                                    device.getKey()));
                        });
        return deviceObjects;
    }

    /**
     * Requests up to date list of devices from TinyMesh cloud.
     *
     * @return a list of devices available.
     */
    @Nullable
    public List<DoorSensorJSON> requestDevices() {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpEntity<String> entity = getHTTPEntity();
        String url = UriComponentsBuilder
                .fromHttpUrl(baseURL)
                .path("/v2/device/{networkID}")
                .buildAndExpand(networkID)
                .toUriString();

        ResponseEntity<String> response = null;

        try {
            response = restTemplate.exchange(url, GET, entity, String.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            return null;
        }
        try {
            return objectMapper.readValue(response.getBody(), new TypeReference<List<DoorSensorJSON>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Requests specific device from TinyMesh cloud
     *
     * @param oid id of the device being requested
     * @return returns JSON mapped to pojo.
     */
    @Nullable
    public DoorSensorJSON requestDevice(String oid) {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpEntity<String> entity = getHTTPEntity();
        String url = UriComponentsBuilder
                .fromHttpUrl(baseURL)
                .path("/v2/device/{networkID}/{oid}")
                .buildAndExpand(networkID, oid)
                .toUriString();

        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(url, GET, entity, String.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            return null;
        }

        try {
            return objectMapper.readValue(response.getBody(), DoorSensorJSON.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Helper method to create headers for requests.
     *
     * @return returns HttpEntity<String>
     */
    private HttpEntity<String> getHTTPEntity() {
        String credentials = email + ":" + pass;
        byte[] encodedAuthHeaderValue = Base64.encodeBase64(credentials.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuthHeaderValue);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authHeader);
        return new HttpEntity<>(headers);
    }
}

