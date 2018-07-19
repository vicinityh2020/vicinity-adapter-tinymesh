package com.tinymesh.vicinity.adapter.controller;

import com.tinymesh.vicinity.adapter.entity.Device;
import com.tinymesh.vicinity.adapter.model.ObjectInfo;
import com.tinymesh.vicinity.adapter.repository.DeviceRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.tinymesh.vicinity.adapter.controller.ObjectsApiController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MapDeviceDataToObjectInfoTest {
    private List<Device> deviceList;
    private List<ObjectInfo> objectInfoList;
    private ObjectsApiController controller;

    @Before
    public void setup() {
        controller = new ObjectsApiController();
        deviceList = new ArrayList<>();
        deviceList.add(new Device("Device1", "door-sensor", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com", 1,
                                  "ads"));

    }

    @Test
    public void testMapDeviceDataToObjectInfoCorrectMapping() {
        objectInfoList = controller.mapDeviceDataToObjectInfo(deviceList);

        assertEquals(deviceList.get(0).getDeviceName(), objectInfoList.get(0).getName());
        assertEquals("adap:DoorSensor", objectInfoList.get(0).getType());
        assertEquals(deviceList.get(0).getUuid(), objectInfoList.get(0).getOid());
    }

    @Test
    public void testMapDeviceDataToObjectInfoReturnsNonEmptyList() {
        objectInfoList = controller.mapDeviceDataToObjectInfo(deviceList);

        assertNotNull(objectInfoList);
        assertEquals(1, objectInfoList.size());
    }

    @Test
    public void testMapDeviceDataToObjectInfoEmptyInput() {
        // make sure that input is empty
        deviceList = new ArrayList<>();
        objectInfoList = controller.mapDeviceDataToObjectInfo(deviceList);

        assertNotNull(objectInfoList);
        assertEquals(0, objectInfoList.size());
    }
}
