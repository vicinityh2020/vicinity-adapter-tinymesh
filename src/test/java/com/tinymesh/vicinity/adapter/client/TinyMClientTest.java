package com.tinymesh.vicinity.adapter.client;

import com.tinymesh.vicinity.adapter.jsonmodels.DeviceProperties;
import com.tinymesh.vicinity.adapter.jsonmodels.DoorSensor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TinyMClientTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    TinyMClient client;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getDoorSenors() {
        ResponseEntity responseEntity = mock(ResponseEntity.class);
        RestTemplate restTemplate = mock(RestTemplate.class);

        DoorSensor doorSensor = new DoorSensor();
        doorSensor.setKey("gidKaYtHvUVOo");
        doorSensor.setLocation(new String[0]);

        DeviceProperties deviceProperties = new DeviceProperties();
        deviceProperties.setHardware("2.01");
        deviceProperties.setFirmaware("2.04");

        doorSensor.setDeviceProperties(deviceProperties);
        doorSensor.setNetwork("T");
        doorSensor.setType("building-sensor-v3");
        doorSensor.setAddress(1074135040);
        doorSensor.setName("Test B - 119 sek CO2, 10 min intervall");
        doorSensor.setProvisioned("active");

        Mockito.when(responseEntity.getBody()).thenReturn(doorSensor);
        Mockito.when(responseEntity.getStatusCode()).thenReturn(HttpStatus.OK);

        Mockito.when(restTemplate.exchange(
                Mockito.anyString(),
                Mockito.any(HttpMethod.class),
                Mockito.any(HttpEntity.class),
                Mockito.any(Class.class)
        )).thenReturn(responseEntity);

        /*

        Mockito.when(restTemplate.exchange(
                Mockito.anyString(),
                Mockito.any(HttpMethod.class),
                Mockito.any(ResponseEntity.class),
                Mockito.any(Class.class)
                ))
                .thenReturn(responseEntity);
                */

        ResponseEntity<DoorSensor> response = client.getDoorSenors();
//        Mockito.verify(restTemplate, Mockito.atLeast(1)).;
//        assertEquals(response.getStatusCode(), HttpStatus.OK);
    //assertNotNull(response.getBody());
        assertNotNull(response);


    }
}