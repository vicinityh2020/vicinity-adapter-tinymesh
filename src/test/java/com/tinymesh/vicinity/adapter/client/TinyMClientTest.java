package com.tinymesh.vicinity.adapter.client;

import com.tinymesh.vicinity.adapter.jsonmodels.DoorSensorJSON;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TinyMClientTest {
    private BufferedReader in = null;
    private String respBody;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TinyMClient client;

    @Before
    public void setUp() throws Exception {
        ReflectionTestUtils.setField(client, "baseURL", "https://http.cloud.tiny-mesh.com");
        ReflectionTestUtils.setField(client, "pass", "***REMOVED***");
        ReflectionTestUtils.setField(client, "email", "test@tiny-mesh.com");
        MockitoAnnotations.initMocks(this);
        in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/deviceJSONTestData.json")));
        Optional<String> optionalRespBody = in.lines().reduce(String::concat);
        optionalRespBody.ifPresent(s -> respBody = s);
    }

    @After
    public void teardown() throws IOException {
        if (in != null) {
            in.close();
        }
        in = null;
    }

    @Test
    public void requestDevices() throws Exception {
        ResponseEntity<String> testEntity = new ResponseEntity<>(respBody, HttpStatus.OK);
        Mockito.when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                any(),
                Mockito.<Class<String>>any())
        ).thenReturn(testEntity);
        List<DoorSensorJSON> listOfObjects = client.requestDevices();
        assertNotNull(listOfObjects);
        assertTrue(listOfObjects.size() > 0);
    }

    @Test
    public void tinyMClientDevicesChunckedRespones() {
    }

    @Test
    public void syncDevices() {
    }
}