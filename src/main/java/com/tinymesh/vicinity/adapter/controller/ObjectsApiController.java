package com.tinymesh.vicinity.adapter.controller;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tinymesh.vicinity.adapter.entity.Device;
import com.tinymesh.vicinity.adapter.entity.DeviceUtilization;
import com.tinymesh.vicinity.adapter.model.*;
import com.tinymesh.vicinity.adapter.repository.DeviceRepository;
import com.tinymesh.vicinity.adapter.serializers.ThingDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * The class is responsible for providing a REST-ful API service
 * and serve JSON to incoming HTTP requests
 */
@RestController
public class ObjectsApiController {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private ObjectMapper mapper;
    public ObjectsApiController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public ObjectsApiController() {
    }

    /**
     * Helper method which translates a given list of device-utilization objects (DB entity) {@link DeviceUtilization}
     * to a list of objects {@link ObjectInfo}
     *
     * @param deviceUtilizationList list to be converted
     * @return returns a new list of objects suitable for JSON display
     */

    /**
     * Helper method which translates a given list of devices (DB entity) {@link Device}
     * to a list of objects (JSON model) {@link ObjectInfo}
     *
     * @param deviceList a list to be converted
     * @return returns a new list of objects suitable for JSON display
     * @see ObjectProperty
     * @see LinkInfo
     * @see OutputSchema
     */
    public List<ObjectInfo> mapDeviceDataToObjectInfo(List<Device> deviceList) {
        List<ObjectInfo> items = new ArrayList<>();

        for (Device device : deviceList) {
            ObjectInfo objectInfo = new ObjectInfo();
            List<ObjectProperty> objectProperties = new ArrayList<>();
            objectInfo.setOid(device.getUuid());
            objectInfo.setName(device.getDeviceName());
            objectInfo.setType("adap:DoorSensor");
            objectInfo.setActions(new ArrayList<>());
            objectInfo.setEvents(new ArrayList<>());

            LinkInfo linkInfo = new LinkInfo();
            linkInfo.setHref("/objects/" + device.getUuid() + "/properties/state");
            linkInfo.setMediaType("application/json");
            OutputSchema outputSchema = new OutputSchema();
            outputSchema.setDatatype("boolean");
            outputSchema.setUnits("occupancy");
            ObjectProperty prop = new ObjectProperty();
            prop.setPid("state");
            prop.setMonitors("adap:EntryExit");
            prop.addReadLinksItem(linkInfo);
            prop.setOutput(outputSchema);
            objectProperties.add(prop);

            objectInfo.properties(objectProperties);

            items.add(objectInfo);
        }
        return items;
    }

    public JsonNode mapDeviceDataToObjectInfo2(List<Device> deviceList) {
        ArrayNode items = mapper.createArrayNode();
        ThingDescription td = new ThingDescription(mapper);
        for (Device device : deviceList) {
            ObjectNode on = td.getThingDescFromDevice(device);
            items.add(on);
        }
        JsonNode res = td.getThingDescWrapper();
        ((ObjectNode) res).set("thing-descriptions", items);
        return res;
    }

    /**
     * Endpoint; Fetches a list of all {@link ObjectInfo} objects from the TinyMesh cloud.
     * Produces a JSON response containing the information about the objects and their attributes.
     *
     * @return ResponseEntity of type list, containing the {@link ObjectInfo} objects with
     * http status 200 on success, or http status 404 if no objects exist.
     * @see ResponseEntity
     */
    @RequestMapping(value = "/objects", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<JsonNode> getObjects() {

        JsonNode objectList;
        List<Device> deviceList = deviceRepository.findAll();

        if (deviceList.isEmpty()) {
            return new ResponseEntity<>((JsonNode) mapper.createObjectNode(), HttpStatus.NOT_FOUND);
        }

        objectList = this.mapDeviceDataToObjectInfo2(deviceList);
        return new ResponseEntity<>(objectList, HttpStatus.OK);
    }

    /**
     * Fallback method which handles all the IllegalArgumentExceptions
     *
     * @param response an {@link HttpServletResponse} object containing the response attributes
     * @throws IOException in case a response cannot be sent
     * @see IllegalArgumentException
     * @see IOException
     */
    @ExceptionHandler(IllegalArgumentException.class)
    void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), "Bad request.");
    }

    /**
     * Endpoint; Gets the last known value for a property {@link PropertyValue}.
     *
     * @param oid a unique object id used to address a specific object
     * @param pid a unique property id used to address a specific property
     * @return The endpoint returns state from database, which represents the last received value from TinyMesh Cloud.
     * HTTP.404 if the device does not exist.
     * HTTP.404 if the pid does not match any properties defined in the thing description (T332).
     * HTTP.200 if a matching uuid and property is found.
     * @see ResponseEntity
     * @see Device
     */
    @RequestMapping(value = "/objects/{oid}/properties/{pid}",
            method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity<PropertyValue> getObjectProperty(@PathVariable UUID oid, @PathVariable String pid) {

        HttpHeaders responseHeaders = new HttpHeaders();
        PropertyValue jsonBody = new PropertyValue();
        HttpStatus status = HttpStatus.NOT_FOUND;

        Optional<Device> deviceOptional = deviceRepository.findById(oid);

        // Check if the device is present in the database
        Device device = deviceOptional.orElse(null);
        if (device != null) {

            // condition check for 'state'
            if (pid.equals("state")) {

                Boolean state = device.isState();
                LocalDateTime time = device.getDateTime().withNano(0);

                if (state != null) {
                    // State is not null, therefore add the 'Last-Modified' header
                    responseHeaders.setLastModified(Timestamp.valueOf(time).getTime());
                }
                jsonBody.setTimestamp(time.atOffset(ZoneOffset.UTC));
                jsonBody.setValue(state);
                status = HttpStatus.OK;
            }
        }

        return new ResponseEntity<>(jsonBody, responseHeaders, status);
    }

    @RequestMapping(value = "/objects/{oid}/properties/{pid}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity<PropertyValue> setObjectProperty(@PathVariable UUID oid, @PathVariable String pid, @RequestBody SetPropertyValue body) {
        return new ResponseEntity<>(new PropertyValue(), HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(value = "/objects/{oid}/actions/{aid}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<PropertyValue> getObjectActionStatus(@PathVariable UUID oid, @PathVariable String aid, @RequestBody ExecActionPayload body) {
        return new ResponseEntity<>(new PropertyValue(), HttpStatus.NOT_IMPLEMENTED);
    }


    @RequestMapping(value = "/objects/{oid}/actions/{aid}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity<PropertyValue> executeObjectAction(@PathVariable UUID oid, @PathVariable String aid) {
        return new ResponseEntity<>(new PropertyValue(), HttpStatus.NOT_IMPLEMENTED);
    }
}
