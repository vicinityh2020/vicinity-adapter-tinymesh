package com.tinymesh.vicinity.adapter.api;

import com.tinymesh.vicinity.adapter.model.ExecActionPayload;
import com.tinymesh.vicinity.adapter.model.SetPropertyValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

import com.tinymesh.vicinity.adapter.model.ObjectInfo;
import com.tinymesh.vicinity.adapter.model.PropertyValue;

@RestController
public class ObjectsApiController {

    private static Map<UUID, ObjectInfo> objects = new HashMap<>();

    public ObjectsApiController() {
        if (objects.isEmpty()) {
            UUID[] uuids = {
                UUID.fromString("c23bf592-9885-4572-90fe-bc9f68bcecdb"),
                UUID.fromString("b44aec7a-1108-4ff9-bc75-46b6d41d653b"),
                UUID.fromString("901a9f17-7b74-4e44-9eb1-8ce6330c781c"),
                UUID.fromString("04541225-c518-4dec-841f-484ce425550d"),
                UUID.fromString("69705746-91ba-493b-9158-3bb9e7e228b3")
            };

            int i = 1;
            for ( UUID oid : uuids) {
                ObjectInfo obj = new ObjectInfo();
                obj.setOid(oid);
                obj.setType("door-state");
                obj.setName("Door - " + i);
                objects.put(oid, obj);
                i++;
            }
        }
    }


    @RequestMapping(value = "/objects", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<ObjectInfo>> getObjects() {
        List<ObjectInfo> objectList = new ArrayList<>(objects.values());
        return new ResponseEntity<>(objectList, HttpStatus.OK);
    }

    @RequestMapping(value = "/objects/{oid}/properties/{pid}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<PropertyValue> getObjectProperty(@PathVariable UUID oid, @PathVariable String pid) {
        return new ResponseEntity<>(new PropertyValue(), HttpStatus.NOT_IMPLEMENTED);
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
