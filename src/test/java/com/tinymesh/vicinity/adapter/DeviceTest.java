package com.tinymesh.vicinity.adapter;

import com.tinymesh.vicinity.adapter.database.SetDataToDevice;
import org.junit.Before;
import org.junit.Test;

public class DeviceTest {

    //@ElementCollection
    //List<Device> deviceList =  new ArrayList<>();


    @Before
    public void setup() {
    }

    @Test
    public void getAllObjects() {


       // deviceList.add(new Device("Device1", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com"));
        SetDataToDevice setDataToDevice = new SetDataToDevice();
        setDataToDevice.setData();
//        setDataToDevice.retrive();
    }
   /* @Test
    public void testAssertTrue(){
        assertTrue("fail", true);
    }
    */
}
