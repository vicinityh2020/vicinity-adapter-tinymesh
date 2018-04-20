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

    ArrayList<UUID> oids;
    ArrayList<Device> mockDevices;
    Boolean[] states = {true, null};

    private UUID nonExistentUuid;

    private final int maxPositiveTests = 2;

    @Before
    public void setUp() throws Exception {
        oids = new ArrayList<>();
        mockDevices = new ArrayList<>();

        for (int i = 0; i < maxPositiveTests; i++) {
            UUID uuid = UUID.randomUUID();
            Device device = new Device("MockTestDevice" + String.valueOf(i), "MockType" + String.valueOf(i),
                    uuid, LocalDateTime.now(), states[i], "www.mockurl.com", i);

            oids.add(uuid);
            mockDevices.add(device);

            when(deviceRepository.findById(uuid)).thenReturn(Optional.of(device));
        }

        nonExistentUuid = UUID.randomUUID();
        when(deviceRepository.findById(nonExistentUuid)).thenReturn(Optional.empty());
    }

    @Test
    public void getObjectProperty() throws Exception {
        for (int i = 0; i < mockDevices.size(); i++) {
            Device mockDevice = mockDevices.get(i);

            ResponseEntity<PropertyValue> response = controller.getObjectProperty(mockDevice.getUuid(), "state");
            assertEquals(HttpStatus.OK, response.getStatusCode());

            Boolean stateValue = (Boolean) response.getBody().getValue();
            assertEquals(mockDevice.isState(), stateValue);

            if (stateValue != null) {
                assertTrue(response.getHeaders().containsKey("Last-Modified"));

                // withNano(0) to omit nanoseconds

                long expectedTime = Timestamp.valueOf(mockDevice.getDateTime().withNano(0)).getTime();
                assertEquals(expectedTime, response.getHeaders().getLastModified());
            }
        }

        // fail on non existent uuid
        ResponseEntity<PropertyValue> notFoundResponse = controller.getObjectProperty(nonExistentUuid, "state");
        assertEquals(HttpStatus.NOT_FOUND, notFoundResponse.getStatusCode());
    }

}