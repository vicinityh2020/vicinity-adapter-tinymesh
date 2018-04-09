package com.tinymesh.vicinity.adapter.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinymesh.vicinity.adapter.jsonmodels.DoorSensorJSON;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class TinyMClient{

    private RestTemplate restTemplate;

    public TinyMClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public DoorSensorJSON getDoorSenors(){
        ObjectMapper objectMapper = new ObjectMapper();
        DoorSensorJSON doorSensorJSON;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("testHeader", "Test ");

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());


        //Tester i httpbin.org
        String url = "http://localhost:1080/v2/device/nid";

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        try {
            doorSensorJSON = objectMapper.readValue(result.getBody(), DoorSensorJSON.class);
            return doorSensorJSON;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}