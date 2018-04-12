package com.tinymesh.vicinity.adapter.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinymesh.vicinity.adapter.database.Device;
import com.tinymesh.vicinity.adapter.jsonmodels.DoorSensorJSON;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
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

    public TinyMClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Device> syncDevices(){
        List<DoorSensorJSON> devices = this.requestDevices();
        List<Device> deviceObjects = new ArrayList<>();

        //filter out devices that are not provisioned as per description in T330
        //and creates Device objects suitable for DB
        devices.stream().filter(device -> device.getProvisioned().equals("active")).forEach(device -> {
            String deviceURL = baseURL + "/v2/device/" + device.getNetwork() + "/" + device.getKey();
            deviceObjects.add(new Device(device.getName(), device.getType(), UUID.randomUUID(), LocalDateTime.now(), true, deviceURL));
        });
        return deviceObjects;
    }

    public List<DoorSensorJSON> requestDevices(){
        ObjectMapper objectMapper = new ObjectMapper();
        HttpEntity<String> entity = getHTTPEntity();

        String url = baseURL + "/v2/device/T";
        ResponseEntity<String> response = restTemplate.exchange(url, GET, entity, String.class);

        try {
            return objectMapper.readValue(response.getBody(), new TypeReference<List<DoorSensorJSON>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DoorSensorJSON requestDevice(String oid){
        ObjectMapper objectMapper = new ObjectMapper();
        HttpEntity<String> entity = getHTTPEntity();

        String url = baseURL + "/v2/device/T/" + oid;
        ResponseEntity<String> response = restTemplate.exchange(url, GET, entity, String.class);

        try {
            return objectMapper.readValue(response.getBody(), DoorSensorJSON.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpEntity<String> getHTTPEntity() {
        String credentials = email + ":" + pass;
        byte[] encodedAuthHeaderValue = Base64.encodeBase64(credentials.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuthHeaderValue);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authHeader);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return entity;
    }

}

