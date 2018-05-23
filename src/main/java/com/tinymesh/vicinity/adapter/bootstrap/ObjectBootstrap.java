package com.tinymesh.vicinity.adapter.bootstrap;

import com.tinymesh.vicinity.adapter.client.TinyMClient;
import com.tinymesh.vicinity.adapter.entity.Device;
import com.tinymesh.vicinity.adapter.repository.DeviceRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;

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
        List<Device> devices = tinyMClient.syncDevices();
        try {
            this.deviceRepository.saveAll(devices);
        } catch (DataIntegrityViolationException e) { // catches exception in case we already have the data

        }
    }
}
