package com.tinymesh.vicinity.adapter.bootstrap;

import com.tinymesh.vicinity.adapter.client.StreamTinyMCloud;
import com.tinymesh.vicinity.adapter.client.TinyMClient;
import com.tinymesh.vicinity.adapter.database.Device;
import com.tinymesh.vicinity.adapter.database.DeviceDataHandler;
import com.tinymesh.vicinity.adapter.jsonmodels.DoorSensorJSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@Component
public class ObjectBootstrap implements ApplicationListener<ContextRefreshedEvent>{
    private TinyMClient tinyMClient;
    @Value("${tinymesh.client.base_url}")
    String baseURL;

    public ObjectBootstrap(TinyMClient tinyMClient) {
        this.tinyMClient = tinyMClient;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<DoorSensorJSON> devices = tinyMClient.requestDevices();
        DeviceDataHandler deviceDataHandler = DeviceDataHandler.getInstance();
        List<Device> deviceObjects = new ArrayList<>();
        for (DoorSensorJSON device : devices){
            String deviceURL = baseURL + "/v2/device/" + device.getNetwork() + "/" + device.getKey();
            deviceObjects.add(new Device(device.getName(), device.getType(), UUID.randomUUID(), LocalDateTime.now(), true, deviceURL));
        }
        deviceDataHandler.setData(deviceObjects);
    }
}
