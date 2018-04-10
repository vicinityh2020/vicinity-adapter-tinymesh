package com.tinymesh.vicinity.adapter.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinymesh.vicinity.adapter.jsonmodels.DoorSensorJSON;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Service
public class TinyMClient {

    private RestTemplate restTemplate;

    public TinyMClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<DoorSensorJSON> requestDevices(){
        ObjectMapper objectMapper = new ObjectMapper();
        String email = "test@tiny-mesh.com";
        String pass = "***REMOVED***";
        String credentials = email + ":" + pass;
        byte[] encodedAuthHeaderValue = Base64.encodeBase64(credentials.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuthHeaderValue);

        //String result = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authHeader);
        HttpEntity<String> entity = new HttpEntity<>(headers);


        ResponseEntity<String> response = restTemplate.exchange("https://http.cloud.tiny-mesh.com/v2/device/T/", GET, entity, String.class);
        System.out.println(response.getBody());


        try {
            List<DoorSensorJSON> objects = objectMapper.readValue(response.getBody(), new TypeReference<List<DoorSensorJSON>>(){});
            return objects;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;


    }

}
