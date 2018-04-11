package com.tinymesh.vicinity.adapter.client;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TinyMClientTest {



    RestTemplate restTemplate;
    TinyMClient client;

    MockMvc mockMvc;

    String email = "test@tiny-mesh.com";
    String pass = "***REMOVED***";
    String credentials = email + ":" + pass;
    byte[] encodedAuthHeaderValue = Base64.encodeBase64(credentials.getBytes(Charset.forName("US-ASCII")));
    String authHeader = "Basic " + new String(encodedAuthHeaderValue);

    @Before
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
        client = new TinyMClient(restTemplate);


    }

    @Test
    public void tinyMClientDevices(){


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authHeader);
        HttpEntity<String> entity = new HttpEntity<>(headers);


        ResponseEntity<String> response = restTemplate.exchange("https://http.cloud.tiny-mesh.com/v2/device/T/", GET, entity, String.class);

        assertNotNull(response);

        String matcher = response.getBody();
        String type = "type";
        String name = "name";
        String key = "key";
        String provisioned = "provisioned";
        assertThat(matcher, containsString(type));
        assertThat(matcher, containsString(name));
        assertThat(matcher, containsString(key));
        assertThat(matcher, containsString(provisioned));
    }

//    @Test
    public void requestGetStatusOK() throws Exception {
        mockMvc.perform(get("https://http.cloud.tiny-mesh.com/v2/device/T/"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void tinyMClientDevicesChunckedRespones(){


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authHeader);
        HttpEntity<String> entity = new HttpEntity<>(headers);




/*
        ResponseEntity<String> response = restTemplate.exchange(
                "https://http.cloud.tiny-mesh.com/v2/messages/T?date.from=NOW//-5MINUTE&data.encoding=hex&continuous=true&stream=true",
                GET, entity, String.class);
 */







    }
}