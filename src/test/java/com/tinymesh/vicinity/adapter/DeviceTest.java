package com.tinymesh.vicinity.adapter;

import com.tinymesh.vicinity.adapter.api.ObjectsApiController;
import com.tinymesh.vicinity.adapter.database.Device;
import com.tinymesh.vicinity.adapter.database.DeviceDataHandler;
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
       // List<Device> retrievedDevices = deviceDataHandler.retrieveData();


        List<ObjectInfo> objectInfoList = objectsApiController.mapDataToObjectInfo(deviceList);
        Device fstDevice = deviceList.get(1);
        ObjectInfo fstObjInfo = objectInfoList.get(1);
        ObjectsApiController objectsApiController = new ObjectsApiController();

        assertEquals(fstDevice.getDeviceName(), fstObjInfo.getName());
        assertEquals(fstDevice.getDeviceType(), fstObjInfo.getType());
        assertEquals(fstDevice.getUuid(), fstObjInfo.getOid());


        //DeviceUtilTest

        Device device = new Device("Device1","Sensor", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com");

        //deviceUtilList.add(new DeviceUtilization(UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), 4, device.getUuid()));

        DeviceUtilization deviceUtilization = new DeviceUtilization(UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), 4, device.getUuid());
        System.out.println(deviceUtilization);

        //DeviceUtilDataHandler deviceUtilDataHandler = DeviceUtilDataHandler.getInstance();
        // deviceUtilDataHandler.setData(deviceUtilList);
        // assertEquals(deviceDataHandler,objectsApiController);
        //deviceDataHandler.retrieveData();
    }
}
