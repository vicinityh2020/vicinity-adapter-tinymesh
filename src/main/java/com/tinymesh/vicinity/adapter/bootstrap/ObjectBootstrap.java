package com.tinymesh.vicinity.adapter.bootstrap;

import com.tinymesh.vicinity.adapter.client.TinyMClient;
import com.tinymesh.vicinity.adapter.jsonmodels.DoorSensorJSON;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

@Component
public class ObjectBootstrap implements ApplicationListener<ContextRefreshedEvent>{
    private TinyMClient tinyMClient;

    public ObjectBootstrap(TinyMClient tinyMClient) {
        this.tinyMClient = tinyMClient;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<DoorSensorJSON> devices = tinyMClient.requestDevices();
        for (DoorSensorJSON device : devices){
            System.out.println(device);
            DoorSensorJSON test =  tinyMClient.requestDevice(device.getKey());
            System.out.println(test);
        }
    }
}
