package com.tinymesh.vicinity.adapter.controller;

import com.tinymesh.vicinity.adapter.entity.Device;
import com.tinymesh.vicinity.adapter.model.PropertyValue;
import com.tinymesh.vicinity.adapter.repository.DeviceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class ObjectsApiControllerTest {

    @Mock
    DeviceRepository deviceRepository;

    @InjectMocks
    ObjectsApiController controller;

    ArrayList<Device> mockDevices;
    Boolean[] states = {true, null};

    private UUID nonExistentUuid;

    private final int maxPositiveTests = 2;

    @Before
    public void setUp() throws Exception {

        mockDevices = new ArrayList<>();

        for (int i = 0; i < maxPositiveTests; i++) {
            UUID uuid = UUID.randomUUID();
            Device device = new Device("MockTestDevice" + String.valueOf(i), "MockType" + String.valueOf(i),
                    uuid, LocalDateTime.now(), states[i], "www.mockurl.com", i, "asd");

            mockDevices.add(device);

            when(deviceRepository.findById(uuid)).thenReturn(Optional.of(device));
        }

        nonExistentUuid = UUID.randomUUID();
        when(deviceRepository.findById(nonExistentUuid)).thenReturn(Optional.empty());
    }

    @Test
    public void oidIsPresentTest() throws Exception {
        ResponseEntity<PropertyValue> response = controller.getObjectProperty(mockDevices.get(0).getUuid(), "state");
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<PropertyValue> notFoundResponse = controller.getObjectProperty(nonExistentUuid, "state");
        assertEquals(HttpStatus.NOT_FOUND, notFoundResponse.getStatusCode());
    }

    @Test
    public void propertyNotPresentTest() throws Exception {
        ResponseEntity<PropertyValue> notFoundResponse = controller.getObjectProperty(mockDevices.get(0).getUuid(), "_test_nostate");
        assertEquals(HttpStatus.NOT_FOUND, notFoundResponse.getStatusCode());
    }

    @Test
    public void lastModifiedHeaderTest() throws Exception {
        for (Device mockDevice : mockDevices) {
            ResponseEntity<PropertyValue> response = controller.getObjectProperty(mockDevice.getUuid(), "state");

            boolean lastModifiedPresent = response.getHeaders().containsKey("Last-Modified");

            if (response.getBody().getValue() != null) {
                assertTrue(lastModifiedPresent);

                // withNano(0) to omit nanoseconds
                long expectedTime = Timestamp.valueOf(mockDevice.getDateTime().withNano(0)).getTime();
                assertEquals(expectedTime, response.getHeaders().getLastModified());
            } else {
                assertFalse(lastModifiedPresent);
            }
        }
    }
}