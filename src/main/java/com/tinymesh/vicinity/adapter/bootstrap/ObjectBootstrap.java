package com.tinymesh.vicinity.adapter.bootstrap;

import com.tinymesh.vicinity.adapter.database.Device;
import com.tinymesh.vicinity.adapter.database.DeviceDataHandler;
import com.tinymesh.vicinity.adapter.database.DeviceUtilization;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ObjectBootstrap implements ApplicationListener<ContextRefreshedEvent>{
    private List<Device> deviceList;
    private List<DeviceUtilization> deviceUtilizations;

    public ObjectBootstrap() {
        deviceList = new ArrayList<>();
        deviceUtilizations = new ArrayList<>();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        createDeviceObjects();
        createDeviceUtilObjects();

    }

    private void createDeviceObjects(){
        deviceList.add(new Device("Device1","Sensor", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com"));
        deviceList.add(new Device("Device2","Sensor2", UUID.randomUUID(), LocalDateTime.now(), false, "www.test2.com"));
        deviceList.add(new Device("Device3","Sensor3", UUID.randomUUID(), LocalDateTime.now(), false, "www.test3.com"));
        deviceList.add(new Device("Device4","Sensor4", UUID.randomUUID(), LocalDateTime.now(), false, "www.test4.com"));
        deviceList.add(new Device("Device5","Sensor5", UUID.randomUUID(), LocalDateTime.now(), false, "www.test5.com"));



        DeviceDataHandler deviceDataHandler = DeviceDataHandler.getInstance();
        deviceDataHandler.setData(deviceList);
        //DeviceUtilDataHandler deviceUtilDataHandler = DeviceUtilDataHandler.getInstance();
        //deviceUtilDataHandler.setData(deviceUtilizations);
    }

    private void createDeviceUtilObjects(){
       // deviceUtilizations.add(new DeviceUtilization(UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), 1, deviceList.get(0).getUuid()));
        // deviceUtilizations.add(new DeviceUtilization(UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), 12, deviceList.get(1).getUuid()));

    }
}
