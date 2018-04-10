package com.tinymesh.vicinity.adapter.bootstrap;

import com.tinymesh.vicinity.adapter.client.TinyMClient;
import com.tinymesh.vicinity.adapter.client.TinyMCloudConnection;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

@Component
public class ObjectBootstrap implements ApplicationListener<ContextRefreshedEvent>{



    //private List<Device> deviceList;
    //private List<DeviceUtilization> deviceUtilizations;
    private TinyMCloudConnection tinyMCloudConnection;
    private TinyMClient tinyMClient;

    public ObjectBootstrap(TinyMCloudConnection tinyMCloudConnection, TinyMClient tinyMClient) {
        this.tinyMCloudConnection = tinyMCloudConnection;
        this.tinyMClient = tinyMClient;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        tinyMCloudConnection.requestGet();
    }

    private void createDeviceObjects(){



        //DeviceUtilDataHandler deviceUtilDataHandler = DeviceUtilDataHandler.getInstance();
        //deviceUtilDataHandler.setData(deviceUtilizations);
    }

    private void createDeviceUtilObjects(){
       // deviceUtilizations.add(new DeviceUtilization(UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), 1, deviceList.get(0).getUuid()));
        // deviceUtilizations.add(new DeviceUtilization(UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), 12, deviceList.get(1).getUuid()));

    }


}
