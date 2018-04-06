package com.tinymesh.vicinity.adapter.client;

import com.tinymesh.vicinity.adapter.jsonmodels.DoorSensorJSON;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.junit.MockServerRule;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertNotNull;
import static org.mockserver.model.JsonBody.json;


public class TinyMClientTest {
    @Rule
    public MockServerRule mockServerRule = new MockServerRule(this, 1080);

    private MockServerClient mockServerClient;

    RestTemplate restTemplate;
    TinyMClient client;

    @Before
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
        client = new TinyMClient(restTemplate);
    }


    @Test
    public void getDoorSensors(){
        mockServerClient.when(HttpRequest.request("/v2/device/nid"))
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