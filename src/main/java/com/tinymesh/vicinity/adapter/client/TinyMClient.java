package com.tinymesh.vicinity.adapter.client;

import com.tinymesh.vicinity.adapter.jsonmodels.DoorSensor;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


public class TinyMClient {


    private RestTemplate restTemplate;

    public TinyMClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    ResponseEntity<DoorSensor> getDoorSenors(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("testHeader", "Test ");

        //Tester i httpbin.org
        String url = "https://httpbin.org/headers";

        HttpEntity<DoorSensor> entity = new HttpEntity<>(headers);
        ResponseEntity<DoorSensor> result = restTemplate.exchange(url, HttpMethod.GET, entity, DoorSensor.class);

        //System.out.println(result.getBody());
        return result;
    }
}