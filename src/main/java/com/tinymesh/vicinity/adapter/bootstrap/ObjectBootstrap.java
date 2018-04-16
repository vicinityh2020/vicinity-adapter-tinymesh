package com.tinymesh.vicinity.adapter.bootstrap;

import com.tinymesh.vicinity.adapter.client.TinyMClient;
import com.tinymesh.vicinity.adapter.repository.DeviceRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ObjectBootstrap implements ApplicationListener<ContextRefreshedEvent>{
    private TinyMClient tinyMClient;
    private DeviceRepository deviceRepository;

    public ObjectBootstrap(TinyMClient tinyMClient, DeviceRepository deviceRepository) {
        this.tinyMClient = tinyMClient;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //DeviceDataHandler deviceDataHandler = DeviceDataHandler.getInstance();
        //deviceDataHandler.setData(tinyMClient.syncDevices());
        this.deviceRepository.saveAll(tinyMClient.syncDevices());
    }
}
