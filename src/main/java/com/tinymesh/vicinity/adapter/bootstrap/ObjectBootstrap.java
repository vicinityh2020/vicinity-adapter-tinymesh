package com.tinymesh.vicinity.adapter.bootstrap;

import com.tinymesh.vicinity.adapter.client.TinyMClient;
import com.tinymesh.vicinity.adapter.repository.DeviceDataHandler;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

//@Component
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
