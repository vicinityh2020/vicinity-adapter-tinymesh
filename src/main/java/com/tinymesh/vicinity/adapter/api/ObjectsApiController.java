package com.tinymesh.vicinity.adapter.api;

import com.tinymesh.vicinity.adapter.model.ExecActionPayload;
import com.tinymesh.vicinity.adapter.model.ObjectInfo;
import com.tinymesh.vicinity.adapter.model.PropertyValue;
import com.tinymesh.vicinity.adapter.model.SetPropertyValue;
import java.util.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-12T13:15:43.539Z")

@Controller
public class ObjectsApiController implements ObjectsApi {

    private static final Logger log = LoggerFactory.getLogger(ObjectsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ObjectsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<PropertyValue> executeObjectAction(@ApiParam(value = "Infrastructure specific identifier of the IoT object",required=true) @PathVariable("oid") UUID oid,@ApiParam(value = "Action identifier (as in object description) (e.g. switch)",required=true) @PathVariable("aid") String aid,@ApiParam(value = "" ,required=true )  @Valid @RequestBody ExecActionPayload body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PropertyValue>(objectMapper.readValue("{  \"value\" : 24.5,  \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"}", PropertyValue.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PropertyValue>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PropertyValue>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<PropertyValue> getObjectActionStatus(@ApiParam(value = "Infrastructure specific identifier of the IoT object",required=true) @PathVariable("oid") UUID oid,@ApiParam(value = "Action identifier (as in object description) (e.g. switch)",required=true) @PathVariable("aid") String aid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PropertyValue>(objectMapper.readValue("{  \"value\" : 24.5,  \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"}", PropertyValue.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PropertyValue>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PropertyValue>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<PropertyValue> getObjectProperty(@ApiParam(value = "Infrastructure specific identifier of the IoT object",required=true) @PathVariable("oid") UUID oid,@ApiParam(value = "Property identifier (as in object description) (e.g. temp1)",required=true) @PathVariable("pid") String pid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PropertyValue>(objectMapper.readValue("{  \"value\" : 24.5,  \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"}", PropertyValue.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PropertyValue>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PropertyValue>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<ObjectInfo>> getObjects() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<ObjectInfo>>(objectMapper.readValue("[ {  \"name\" : \"my device\",  \"oid\" : \"0729a580-2240-11e6-9eb5-0002a5d5c51b\",  \"type\" : \"Thermostate\",  \"actions\" : [ {    \"input\" : {      \"datatype\" : \"boolean\",      \"units\" : \"Adimensional\"    },    \"read_links\" : [ {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    }, {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    } ],    \"affects\" : \"OnOffStatus\",    \"write_links\" : [ {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    }, {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    } ],    \"aid\" : \"switch\"  }, {    \"input\" : {      \"datatype\" : \"boolean\",      \"units\" : \"Adimensional\"    },    \"read_links\" : [ {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    }, {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    } ],    \"affects\" : \"OnOffStatus\",    \"write_links\" : [ {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    }, {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    } ],    \"aid\" : \"switch\"  } ],  \"properties\" : [ {    \"output\" : {      \"datatype\" : \"float\",      \"units\" : \"Celsius\"    },    \"read_links\" : [ {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    }, {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    } ],    \"pid\" : \"temp1\",    \"write_links\" : [ {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    }, {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    } ],    \"monitors\" : \"Temperature\",    \"writable\" : false  }, {    \"output\" : {      \"datatype\" : \"float\",      \"units\" : \"Celsius\"    },    \"read_links\" : [ {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    }, {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    } ],    \"pid\" : \"temp1\",    \"write_links\" : [ {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    }, {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    } ],    \"monitors\" : \"Temperature\",    \"writable\" : false  } ],  \"events\" : [ {    \"eid\" : \"eid\"  }, {    \"eid\" : \"eid\"  } ]}, {  \"name\" : \"my device\",  \"oid\" : \"0729a580-2240-11e6-9eb5-0002a5d5c51b\",  \"type\" : \"Thermostate\",  \"actions\" : [ {    \"input\" : {      \"datatype\" : \"boolean\",      \"units\" : \"Adimensional\"    },    \"read_links\" : [ {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    }, {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    } ],    \"affects\" : \"OnOffStatus\",    \"write_links\" : [ {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    }, {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    } ],    \"aid\" : \"switch\"  }, {    \"input\" : {      \"datatype\" : \"boolean\",      \"units\" : \"Adimensional\"    },    \"read_links\" : [ {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    }, {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    } ],    \"affects\" : \"OnOffStatus\",    \"write_links\" : [ {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    }, {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    } ],    \"aid\" : \"switch\"  } ],  \"properties\" : [ {    \"output\" : {      \"datatype\" : \"float\",      \"units\" : \"Celsius\"    },    \"read_links\" : [ {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    }, {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    } ],    \"pid\" : \"temp1\",    \"write_links\" : [ {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    }, {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    } ],    \"monitors\" : \"Temperature\",    \"writable\" : false  }, {    \"output\" : {      \"datatype\" : \"float\",      \"units\" : \"Celsius\"    },    \"read_links\" : [ {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    }, {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    } ],    \"pid\" : \"temp1\",    \"write_links\" : [ {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    }, {      \"mediaType\" : \"application/json\",      \"href\" : \"properties/temp1\"    } ],    \"monitors\" : \"Temperature\",    \"writable\" : false  } ],  \"events\" : [ {    \"eid\" : \"eid\"  }, {    \"eid\" : \"eid\"  } ]} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<ObjectInfo>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<ObjectInfo>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<PropertyValue> setObjectProperty(@ApiParam(value = "Infrastructure specific identifier of the IoT object",required=true) @PathVariable("oid") UUID oid,@ApiParam(value = "Property identifier (as in object description) (e.g. temp1)",required=true) @PathVariable("pid") String pid,@ApiParam(value = "The value of action to set" ,required=true )  @Valid @RequestBody SetPropertyValue body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PropertyValue>(objectMapper.readValue("{  \"value\" : 24.5,  \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"}", PropertyValue.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PropertyValue>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PropertyValue>(HttpStatus.NOT_IMPLEMENTED);
    }

}