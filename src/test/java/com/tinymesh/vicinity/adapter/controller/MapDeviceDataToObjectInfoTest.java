package com.tinymesh.vicinity.adapter.controller;

import com.tinymesh.vicinity.adapter.entity.Device;
import com.tinymesh.vicinity.adapter.model.ObjectInfo;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.tinymesh.vicinity.adapter.controller.ObjectsApiController.mapDeviceDataToObjectInfo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MapDeviceDataToObjectInfoTest {
    private List<Device> deviceList;
    private List<ObjectInfo> objectInfoList;

    @Before
    public void setup() {
        deviceList = new ArrayList<>();
        deviceList.add(new Device("Device1","Sensor", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com", 1));
    }

    @Test
    public void testMapDeviceDataToObjectInfoCorrectMapping() {
        objectInfoList = mapDeviceDataToObjectInfo(deviceList);

        assertEquals(deviceList.get(0).getDeviceName(), objectInfoList.get(0).getName());
        assertEquals(deviceList.get(0).getDeviceType(), objectInfoList.get(0).getType());
        assertEquals(deviceList.get(0).getUuid(), objectInfoList.get(0).getOid());
    }

    @Test
    public void testMapDeviceDataToObjectInfoReturnsNonEmptyList() {
        objectInfoList = mapDeviceDataToObjectInfo(deviceList);

        assertNotNull(objectInfoList);
        assertEquals(1, objectInfoList.size());
    }

    @Test
    public void testMapDeviceDataToObjectInfoEmptyInput(){
        // make sure that input is empty
        deviceList = new ArrayList<>();
        objectInfoList = mapDeviceDataToObjectInfo(deviceList);

        assertNotNull(objectInfoList);
        assertEquals(0, objectInfoList.size());
    }
}
