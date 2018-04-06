package com.tinymesh.vicinity.adapter.client;

import com.tinymesh.vicinity.adapter.jsonmodels.DeviceProperties;
import com.tinymesh.vicinity.adapter.jsonmodels.DoorSensor;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.integration.ClientAndProxy;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.junit.MockServerRule;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


import static org.mockserver.integration.ClientAndProxy.startClientAndProxy;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

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
        mockServerClient.when(HttpRequest.request("/headers")).respond(HttpResponse.response().withStatusCode(200));

        ResponseEntity<DoorSensor> resp = client.getDoorSenors();
        System.out.println(resp.getStatusCode());
        assertEquals(resp.getStatusCode(), HttpStatus.OK);
    }
}