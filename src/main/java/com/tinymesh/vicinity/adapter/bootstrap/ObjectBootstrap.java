package com.tinymesh.vicinity.adapter.bootstrap;

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

@Component
public class ObjectBootstrap implements ApplicationListener<ContextRefreshedEvent>{
    private TinyMClient tinyMClient;

    public ObjectBootstrap(TinyMClient tinyMClient) {
        this.tinyMClient = tinyMClient;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        DeviceDataHandler deviceDataHandler = DeviceDataHandler.getInstance();
        deviceDataHandler.setData(tinyMClient.syncDevices());
    }
}
