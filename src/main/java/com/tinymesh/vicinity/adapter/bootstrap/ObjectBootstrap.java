package com.tinymesh.vicinity.adapter.bootstrap;

import com.tinymesh.vicinity.adapter.client.TinyMClient;
import com.tinymesh.vicinity.adapter.database.Device;
import com.tinymesh.vicinity.adapter.jsonmodels.DoorSensorJSON;
import org.mockserver.client.server.MockServerClient;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

@Component
public class ObjectBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    //private List<Device> deviceList;
    //private List<DeviceUtilization> deviceUtilizations;
    private TinyMClient tinyMClient;

    public ObjectBootstrap(TinyMClient tinyMClient) {
        this.tinyMClient = tinyMClient;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        getDoorsFromAPI();
    }

    private void createDeviceObjects(){



        //DeviceUtilDataHandler deviceUtilDataHandler = DeviceUtilDataHandler.getInstance();
        //deviceUtilDataHandler.setData(deviceUtilizations);
    }

    private void createDeviceUtilObjects(){
       // deviceUtilizations.add(new DeviceUtilization(UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), 1, deviceList.get(0).getUuid()));
        // deviceUtilizations.add(new DeviceUtilization(UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), 12, deviceList.get(1).getUuid()));

    }

    private void getDoorsFromAPI(){
        new MockServerClient("localhost", 1080)
                .when(request()
                        .withMethod("GET")
                        .withPath("/headers"))
                .respond(response()
                        .withStatusCode(200)
                        .withBody(json(
                                "{\n" +
                                        "  \"key\" : \"gidKaYtHvUVOo\",\n" +
                                        "  \"location\" : [],                \n" +
                                        "  \"proto/tm\" : {                  \n" +
                                        "    \"firmware\" : \"2.01\",\n" +
                                        "    \"hardware\" : \"2.00\"\n" +
                                        "  },\n" +
                                        "  \"network\" : \"T\",                \n" +
                                        "  \"type\" : \"building-sensor-v3\", \n" +
                                        "  \"address\" : 1074135040,      \n" +
                                        "  \"name\" : \"Test B - 119 sek CO2, 10 min intervall\",\n" +
                                        "  \"provisioned\" : \"active\",   \n" +
                                        "  \"meta\" : {\n" +
                                        "    \"updated\" : \"2018-03-19T09:53:50.760Z\",\n" +
                                        "    \"event/date\" : \"2017-06-23T11:20:52.446Z\",  \n" +
                                        "    \"created\" : \"2017-03-17T15:17:58.369Z\",     \n" +
                                        "    \"event/key\" : \"1eq-6jok-djhg-511-VC9naWRLYVl0SHZVVk9v\" \n" +
                                        "  }\n" +
                                        "}"))
                );
        DoorSensorJSON doorSensorJSON = tinyMClient.getDoorSenors();
        if (doorSensorJSON != null){
            Device device = new Device("Device1", "Sensor", UUID.randomUUID(), LocalDateTime.now(),true,"www.test.com");
        }
    }
}
