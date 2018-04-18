package com.tinymesh.vicinity.adapter;

import com.tinymesh.vicinity.adapter.controller.ObjectsApiController;
import com.tinymesh.vicinity.adapter.entity.Device;
import com.tinymesh.vicinity.adapter.entity.DeviceUtilization;
import com.tinymesh.vicinity.adapter.model.ObjectInfo;
import com.tinymesh.vicinity.adapter.repository.DeviceRepository;
import com.tinymesh.vicinity.adapter.repository.DeviceUtilDataHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.ElementCollection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.tinymesh.vicinity.adapter.controller.ObjectsApiController.mapDeviceDataToObjectInfo;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class DeviceTest {
    @Autowired
    private ObjectsApiController objectsApiController;

    @Autowired
    private DeviceRepository deviceRepository;

    ObjectInfo objectInfo = new ObjectInfo();
    @ElementCollection
    private List<Device> deviceList =  new ArrayList<>();
    private List<DeviceUtilization> deviceUtilList =  new ArrayList<>();


    @Before
    public void setup() {
    }

    @Test
    public void getAllObjects() {
        deviceList.add(new Device("Device1","Sensor", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com", 1));
        deviceList.add(new Device("Device2","Sensor", UUID.randomUUID(), LocalDateTime.now(), false, "www.test2.com", 2));
        deviceRepository.saveAll(deviceList);

        List<ObjectInfo> objectInfoList = mapDeviceDataToObjectInfo(deviceList);
        Device fstDevice = deviceList.get(1);
        ObjectInfo fstObjInfo = objectInfoList.get(1);

        assertEquals(fstDevice.getDeviceName(), fstObjInfo.getName());
        assertEquals(fstDevice.getDeviceType(), fstObjInfo.getType());
        assertEquals(fstDevice.getUuid(), fstObjInfo.getOid());


    }
    @Test
    public void getAllObjectsDeviceUtil() {

        Device device = new Device("Device1","Sensor", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com", 1);

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
