package com.tinymesh.vicinity.adapter.connection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinymesh.vicinity.adapter.database.Device;
import com.tinymesh.vicinity.adapter.jsonmodels.DoorSensorJSON;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;


import static org.springframework.http.HttpMethod.GET;

@Service
public class TinyMCloudConnection {

    private RestTemplate restTemplate;

    public TinyMCloudConnection(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Device requestGet(){


        Device device;
        ObjectMapper objectMapper = new ObjectMapper();
        String email = "test@tiny-mesh.com";
        String pass = "***REMOVED***";
        String credentials = email + ":" + pass;
        byte[] encodedAuthHeaderValue = Base64.encodeBase64(credentials.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuthHeaderValue);

        //String result = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
       // headers.set("Fingerprint", "131b9b0bc57e7b14b6699f0ab26df49d3f8d252afb32c8c5f7e409328d3bc61c");
        //headers.set("Key", "fGixJkS6aTjDNFA+S/uGNoEcIyrXcgTbTsv/D8dwAEcJWXsyDYew71Pyh6muWD6ZO3MjLOt95/2OiCom6YTtpg==");
 //       headers.set("Email", "test@tiny-mesh.com ");
   //     headers.set("Pass", "***REMOVED***");

        headers.set("Authorization", authHeader);

        HttpEntity<String> entity = new HttpEntity<>(headers);


        ResponseEntity<String> response = restTemplate.exchange("https://http.cloud.tiny-mesh.com/v2/device/T", GET, entity, String.class);
        System.out.println(response.getBody());

        /*
        HttpStatus statusCode = response.getStatusCode();
        if (statusCode == HttpStatus.OK) {
            result = response.getBody();
        }
        return result;

*/


        try {
            device = objectMapper.readValue(response.getBody(), Device.class);
            return device;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;


    }

}
