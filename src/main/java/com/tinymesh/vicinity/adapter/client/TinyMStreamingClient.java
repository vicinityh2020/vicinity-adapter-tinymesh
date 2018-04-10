package com.tinymesh.vicinity.adapter.client;


import com.tinymesh.vicinity.adapter.database.Device;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;

public class TinyMStreamingClient {

    RestTemplate restTemplate;

    String file = "{\n" +
            " \"received\" : \"2018-04-05T06:01:56.750Z\", // actual reception in cloud\n" +
            " \"datetime\" : \"2018-04-05T06:01:56.749Z\", // sent from device\n" +
            " \"key\" : \"1fi-rmqk-mre8-3vt-VC9naWoyamliWnJZQXpi\", // unique key for the message, contains network and device id\n" +
            " \"proto/tm\" : {  \n" +
            "    \"aio1\" : 2047,           // analogue input 0, encoded as integer\n" +
            "    \"aio0\" : 795,            // analogue input 1, encoded as nteger\n" +
            "    \"rssi\" : 153,            // the signal strength to nearest neighbour \n" +
            "    \"packet_number\" : 29734, // the \n" +
            "    \"data\" : 400,            // configurable data field; not important in this case \n" +
            "    \"latency\" : 0,           // latency in 10ms increments\n" +
            "    \"hops\" : 1,              // number of actual hops in mesh network; network metric\n" +
            "    \"type\" : \"event\",        // type of packet\n" +
            "    \"hw\" : \"1.00\",           // hardware revision\n" +
            "    \"volt\" : 3.12,           // the power supply voltage; device metric\n" +
            "    \"temp\" : 25,             // core temprature of the chip\n" +
            "    \"fw\" : \"1.45\",           // firmware revision\n" +
            "    \"locator\" : 263348420,   // configurable data field; not important in this case\n" +
            "    \"dio\" : { \n" +
            "       \"gpio_6\" : 1,         // digital IO state of GPIO 6\n" +
            "       \"gpio_0\" : 0,         // digital IO state of GPIO 0; same pin as aio0 - unused in this case \n" +
            "       \"gpio_2\" : 1,         // digital IO state of GPIO 2\n" +
            "       \"gpio_4\" : 1,         // digital IO state of GPIO 4\n" +
            "       \"gpio_7\" : 1,         // digital IO state of GPIO 7\n" +
            "       \"gpio_5\" : 0,         // digital IO state of GPIO 5; our movement sensor\n" +
            "       \"gpio_1\" : 0,         // digital IO state of GPIO 1; same pin as aio1 - unused in this case\n" +
            "       \"gpio_3\" : 1          // digital IO state of GPIO 3\n" +
            "    },\n" +
            "    \"network_lvl\" : 1,       // the number of hops the device recides at; network metric\n" +
            "    \"sid\" : 16777226,        // system id, unique pr. network\n" +
            "    \"uid\" : 772079616,       // the unique ID of device, used for identification by cloud\n" +
            "    \"detail\" : \"ima\"         // the type of event\n" +
            " }";


    private void streamData(){


        ResponseEntity<InputStreamResource> responseEntity = restTemplate.getForEntity(file, InputStreamResource.class);

        InputStreamResource image = responseEntity.getBody();

        ResponseEntity<Device> response = restTemplate.exchange("GET/messages", GET, new HttpEntity<>(image), Device.class);


    }



}
