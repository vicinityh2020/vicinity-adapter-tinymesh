package com.tinymesh.vicinity.adapter;

import com.tinymesh.vicinity.adapter.database.SetDataToDevice;
import com.tinymesh.vicinity.adapter.model.Device;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.ElementCollection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeviceTest {

    @ElementCollection
    List<Device> deviceList =  new ArrayList<>();


    @Before
    public void setup() {
    }

    @Test
    public void getAllObjects() {


        deviceList.add(new Device("Device1", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com"));
        SetDataToDevice setDataToDevice = new SetDataToDevice();
        setDataToDevice.setData(deviceList);
//        setDataToDevice.retrive();
    }
   /* @Test
    public void testAssertTrue(){
        assertTrue("fail", true);
    }
    */
}
