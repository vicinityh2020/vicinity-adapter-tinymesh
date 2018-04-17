package com.tinymesh.vicinity.adapter.controller;

import com.tinymesh.vicinity.adapter.client.TinyMClient;
import com.tinymesh.vicinity.adapter.entity.Device;
import com.tinymesh.vicinity.adapter.entity.DeviceUtilization;
import com.tinymesh.vicinity.adapter.model.*;
import com.tinymesh.vicinity.adapter.repository.DeviceRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import static java.util.Collections.singletonList;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoField;
import java.util.*;

@RestController
public class ObjectsApiController {

    // TODO: reorganize lambda in GET/ objects/{oid}/properties/{pid}
    private Device device;

    private TinyMClient tinyMClient;
    private DeviceRepository deviceRepository;
    private static Map<UUID, ObjectInfo> objects = new HashMap<>();

    public ObjectsApiController(TinyMClient tinyMClient, DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
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
        List<Device> deviceList = deviceRepository.findAll();
        objectList = mapDeviceDataToObjectInfo(deviceList);
        return new ResponseEntity<>(objectList, HttpStatus.OK);
    }

    @RequestMapping(value = "/objects/{oid}/properties/{pid}",
                    method = RequestMethod.GET,
                    produces = "application/json")
    public ResponseEntity<PropertyValue> getObjectProperty(@PathVariable String oid, @PathVariable String pid)
            throws HttpClientErrorException {
        UUID uuid;

        // check if the oid has valid format
        try {
            uuid = UUID.fromString(oid);
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();

            // Return HTTP.404
            return new ResponseEntity<>(new PropertyValue(), HttpStatus.NOT_FOUND);
        }

        //TODO: DELETE ME
        uuid = UUID.fromString("ea10fb0e-b3da-4fea-8ccd-818123004ca5");

        HttpHeaders responseHeaders = new HttpHeaders();
        PropertyValue jsonBody = new PropertyValue();
        HttpStatus status = HttpStatus.NOT_FOUND;

        Optional<Device> deviceOptional = deviceRepository.findById(uuid);

        device = null;
        deviceOptional.ifPresent(d -> device = d);

        // Check if the device is present in the database
        if (device != null) {

            // temp condition check for 'state'
            // TODO(@chlenix): replace with a list of predefined properties
            if (pid.equals("state")) {

                Boolean state = device.isState();
                if (state != null) {
                    // State is not null, therefore add the 'Last-Modified' header
                    responseHeaders.setLastModified(Timestamp.valueOf(device.getDateTime()).getTime());
                }
                jsonBody.setTimestamp(OffsetDateTime.now());
                jsonBody.setValue(state);
                status = HttpStatus.OK;
            }
        }

        // return HTTP.404 if the device does not exist OR
        // if the :pid does not match any properties defined in the thing description (T332)
        return new ResponseEntity<>(jsonBody, responseHeaders, status);
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
