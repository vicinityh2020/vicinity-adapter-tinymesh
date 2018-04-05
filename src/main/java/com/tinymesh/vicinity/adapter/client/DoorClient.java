package com.tinymesh.vicinity.adapter.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinymesh.vicinity.adapter.jsonmodels.DoorSensor;
import org.springframework.web.client.RestTemplate;

public class DoorClient {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        DoorSensor doorSensor = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random",
                DoorSensor.class);


        System.out.println(doorSensor.toString());
    }

}


