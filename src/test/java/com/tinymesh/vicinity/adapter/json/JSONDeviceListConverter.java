package com.tinymesh.vicinity.adapter.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tinymesh.vicinity.adapter.database.Device;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JSONDeviceListConverter {

    Device device1 = new Device("Device1","Sensor", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com");

    @Test
    public void convertDeviceListToJson ()throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        //Set printing of json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        //Defining map which will be converted to JSON
        List<Device> deviceList = Stream.of(
                new Device("Device1","Sensor", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com"),
                new Device("Device2","Sensor2", UUID.randomUUID(), LocalDateTime.now(), false, "www.test2.com"))
                .collect(Collectors.toList());



        //Convert List of Device objects to JSON
        String arrayToJson = objectMapper.writeValueAsString(deviceList);
        System.out.println("Convert List of person objects to JSON :");
        System.out.println(arrayToJson);

    }

    //@Test
    public JsonObject converDeviceUUID() throws Exception{
        JsonParser jsonParser = new JsonParser();
        String uuid = device1.getUuid().toString();
        JsonObject objectFromString = jsonParser.parse(uuid).getAsJsonObject();

    return objectFromString;
    }
}
