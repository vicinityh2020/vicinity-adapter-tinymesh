package com.tinymesh.vicinity.adapter.controller;

import com.tinymesh.vicinity.adapter.client.TinyMClient;
import com.tinymesh.vicinity.adapter.entity.Device;
import com.tinymesh.vicinity.adapter.entity.DeviceUtilization;
import com.tinymesh.vicinity.adapter.model.*;
import com.tinymesh.vicinity.adapter.repository.DeviceDataHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.*;

@RestController
public class ObjectsApiController {

    private TinyMClient tinyMClient;
    private static Map<UUID, ObjectInfo> objects = new HashMap<>();

    public ObjectsApiController(TinyMClient tinyMClient) {
        this.tinyMClient = tinyMClient;
    }

    public Map getHashmapObjects() {
        return objects;
    }

    public static List<ObjectInfo> mapDeviceUtilDataToObjectInfo(List<DeviceUtilization> deviceUtilizationList) {
        List<ObjectInfo> items = new ArrayList<>();

        for (DeviceUtilization deviceUtil : deviceUtilizationList) {
            ObjectInfo objectInfo = new ObjectInfo();

            List<ObjectProperty> objectProperties = new ArrayList<>();

            objectInfo.setOid(deviceUtil.getUuid());
            objectInfo.setName(null);
            objectInfo.setType(null);
            objectInfo.setActions(new ArrayList<>());
            objectInfo.setEvents(new ArrayList<>());

            LinkInfo linkInfo = new LinkInfo();
            linkInfo.setHref("properties/state");
            linkInfo.setMediaType("application/json");
            OutputSchema outputSchema = new OutputSchema();
            outputSchema.setDatatype("boolean");
            outputSchema.setUnits("occupancy");
            ObjectProperty prop = new ObjectProperty();
            prop.setPid("state");
            prop.setWritable(false);
            prop.setMonitors("occupancy");
            prop.addReadLinksItem(linkInfo);
            prop.setWriteLinks(new ArrayList<>());
            prop.setOutput(outputSchema);
            objectProperties.add(prop);

            objectInfo.properties(objectProperties);

            items.add(objectInfo);
        }

        return items;
    }

    public static List<ObjectInfo> mapDeviceDataToObjectInfo(List<Device> deviceList) {
        List<ObjectInfo> items = new ArrayList<>();

        for (Device device : deviceList) {
            ObjectInfo objectInfo = new ObjectInfo();

            List<ObjectProperty> objectProperties = new ArrayList<>();

            objectInfo.setOid(device.getUuid());
            objectInfo.setName(device.getDeviceName());
            objectInfo.setType(device.getDeviceType());
            objectInfo.setActions(new ArrayList<>());
            objectInfo.setEvents(new ArrayList<>());

            LinkInfo linkInfo = new LinkInfo();
            linkInfo.setHref("/objects/" + device.getUuid() + "/properties/status");
            linkInfo.setMediaType("application/json");
            OutputSchema outputSchema = new OutputSchema();
            outputSchema.setDatatype("boolean");
            outputSchema.setUnits("occupancy");
            ObjectProperty prop = new ObjectProperty();
            prop.setPid("state");
            prop.setWritable(false);
            prop.setMonitors("occupancy");
            prop.addReadLinksItem(linkInfo);
            prop.setWriteLinks(new ArrayList<>());
            prop.setOutput(outputSchema);
            objectProperties.add(prop);

            objectInfo.properties(objectProperties);

            items.add(objectInfo);
        }

        return items;
    }

    @RequestMapping(value = "/objects/{oid}",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<DoorSensorJSON> getObject(@PathVariable String oid) {
        DoorSensorJSON device = tinyMClient.requestDevice(oid);
        return new ResponseEntity<>(device, HttpStatus.OK);
    }

    @RequestMapping(value = "/objects", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<ObjectInfo>> getObjects() {

        List<ObjectInfo> objectList;
        DeviceDataHandler deviceDataHandler = DeviceDataHandler.getInstance();
        List<Device> deviceList = deviceDataHandler.retrieveData();
        objectList = mapDeviceDataToObjectInfo(deviceList);
        return new ResponseEntity<>(objectList, HttpStatus.OK);
    }

    @RequestMapping(value = "/objects/{oid}/properties/{pid}",
                    method = RequestMethod.GET,
                    produces = "application/json")
    public ResponseEntity<PropertyValue> getObjectProperty(@PathVariable UUID oid, @PathVariable String pid)
            throws HttpClientErrorException {
        try {
            if (pid.equals("getState")){

            }
        } catch (HttpServerErrorException e) {
            e.printStackTrace();
        }
        // if not oid
        return new ResponseEntity<>(new PropertyValue(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/objects/{oid}/properties/{pid}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity<PropertyValue> setObjectProperty(@PathVariable UUID oid, @PathVariable String pid, @RequestBody SetPropertyValue body) {
        try {
            if (pid.equals("setState")) {
                return new ResponseEntity<>(new PropertyValue(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new PropertyValue(), HttpStatus.NOT_FOUND);
            }
        } catch (HttpServerErrorException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new PropertyValue(), HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(value = "/objects/{oid}/actions/{aid}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<PropertyValue> getObjectActionStatus(@PathVariable UUID oid, @PathVariable String aid, @RequestBody ExecActionPayload body) {
        try {
            if (aid.equals("getAction")) {
                return new ResponseEntity<>(new PropertyValue(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new PropertyValue(), HttpStatus.NOT_FOUND);
            }
        } catch (HttpServerErrorException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new PropertyValue(), HttpStatus.NOT_IMPLEMENTED);
    }


    @RequestMapping(value = "/objects/{oid}/actions/{aid}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity<PropertyValue> executeObjectAction(@PathVariable UUID oid, @PathVariable String aid) {
        try {
            if (aid.equals("putAction")) {

                return new ResponseEntity<>(new PropertyValue(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new PropertyValue(), HttpStatus.NOT_FOUND);
            }
        } catch (HttpServerErrorException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new PropertyValue(), HttpStatus.NOT_IMPLEMENTED);
    }
}
