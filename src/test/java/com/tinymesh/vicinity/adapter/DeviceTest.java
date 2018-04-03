package com.tinymesh.vicinity.adapter;

import com.tinymesh.vicinity.adapter.api.ObjectsApiController;
import com.tinymesh.vicinity.adapter.database.Device;
import com.tinymesh.vicinity.adapter.database.DeviceDataHandler;
import com.tinymesh.vicinity.adapter.database.DeviceUtilDataHandler;
import com.tinymesh.vicinity.adapter.database.DeviceUtilization;
import com.tinymesh.vicinity.adapter.model.ObjectInfo;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.ElementCollection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class DeviceTest {

    private ObjectsApiController objectsApiController = new ObjectsApiController();
    ObjectInfo objectInfo = new ObjectInfo();
    @ElementCollection
    private List<Device> deviceList =  new ArrayList<>();
    private List<DeviceUtilization> deviceUtilList =  new ArrayList<>();


    @Before
    public void setup() {
    }

    @Test
    public void getAllObjects() {

        deviceList.add(new Device("Device1","Sensor", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com"));
        deviceList.add(new Device("Device2","Sensor", UUID.randomUUID(), LocalDateTime.now(), false, "www.test2.com"));
        DeviceDataHandler deviceDataHandler = DeviceDataHandler.getInstance();
        deviceDataHandler.setData(deviceList);


        List<ObjectInfo> objectInfoList = objectsApiController.mapDeviceDataToObjectInfo(deviceList);
        Device fstDevice = deviceList.get(1);
        ObjectInfo fstObjInfo = objectInfoList.get(1);

        assertEquals(fstDevice.getDeviceName(), fstObjInfo.getName());
        assertEquals(fstDevice.getDeviceType(), fstObjInfo.getType());
        assertEquals(fstDevice.getUuid(), fstObjInfo.getOid());


    }
    @Test
    public void getAllObjectsDeviceUtil() {

        Device device = new Device("Device1","Sensor", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com");

        deviceUtilList.add(new DeviceUtilization(UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), 4, device.getUuid()));
        System.out.println(deviceUtilList.get(0));
        DeviceUtilDataHandler deviceUtilDataHandler = DeviceUtilDataHandler.getInstance();
        deviceUtilDataHandler.setData(deviceUtilList);
        List<DeviceUtilization> deviceUtilList = deviceUtilDataHandler.retrieveData();
        //deviceUtilList.stream().forEach(System.out::println);


        List<ObjectInfo> objectInfoList = objectsApiController.mapDeviceUtilDataToObjectInfo(deviceUtilList);
        DeviceUtilization fstDeviceUtil = deviceUtilList.get(0);
        ObjectInfo fstObjInfo = objectInfoList.get(0);

        assertEquals(fstDeviceUtil.getUuid(), fstObjInfo.getOid());
        assertEquals(deviceUtilList.get(0).getDeviceUUID(), device.getUuid());


    }
}
