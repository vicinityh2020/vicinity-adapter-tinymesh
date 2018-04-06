package com.tinymesh.vicinity.adapter.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinymesh.vicinity.adapter.jsonmodels.DoorSensor;
import com.tinymesh.vicinity.adapter.jsonmodels.DoorSensorJSON;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.junit.MockServerRule;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockserver.model.Header.header;
import static org.mockserver.model.JsonBody.json;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TinyMClientTest {
    @Rule
    public MockServerRule mockServerRule = new MockServerRule(this, 1080);

    private MockServerClient mockServerClient;

    @Autowired
    RestTemplate restTemplate;

    TinyMClient client;

    @Before
    public void setUp() throws Exception {
        client = new TinyMClient(restTemplate);
    }


    @Test
    public void getDoorSensors(){
        mockServerClient.when(HttpRequest.request("/headers"))
                .respond(HttpResponse.response()
                        .withBody(json(
                        "{\n" +
                        "  \"key\" : \"gidKaYtHvUVOo\",\n" +
                        "  \"location\" : [],                \n" +
                        "  \"proto/tm\" : {                  \n" +
                        "    \"firmware\" : \"2.01\",\n" +
                        "    \"hardware\" : \"2.00\"\n" +
                        "  },\n" +
                        "  \"network\" : \"T\",                \n" +
                        "  \"type\" : \"building-sensor-v3\", \n" +
                        "  \"address\" : 1074135040,      \n" +
                        "  \"name\" : \"Test B - 119 sek CO2, 10 min intervall\",\n" +
                        "  \"provisioned\" : \"active\",   \n" +
                        "  \"meta\" : {\n" +
                        "    \"updated\" : \"2018-03-19T09:53:50.760Z\",\n" +
                        "    \"event/date\" : \"2017-06-23T11:20:52.446Z\",  \n" +
                        "    \"created\" : \"2017-03-17T15:17:58.369Z\",     \n" +
                        "    \"event/key\" : \"1eq-6jok-djhg-511-VC9naWRLYVl0SHZVVk9v\" \n" +
                        "  }\n" +
                        "}"))
                        .withStatusCode(200));

        DoorSensorJSON resp = client.getDoorSenors();

        assertNotNull(resp);
    }
}