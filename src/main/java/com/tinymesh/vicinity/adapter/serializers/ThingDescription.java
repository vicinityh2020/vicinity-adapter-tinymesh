package com.tinymesh.vicinity.adapter.serializers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tinymesh.vicinity.adapter.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ThingDescription {
    private ObjectMapper mapper;

    public ThingDescription(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public ObjectNode getThingDescFromDevice(Device device){
        String deviceType = "core:Device";
        String monitors = "adapters:EntryExit";
        String version = "0.1";
        String pid = "state";

        ArrayNode keywords = mapper.createArrayNode();
        ArrayNode props = mapper.createArrayNode();
        ObjectNode doorState = mapper.createObjectNode();
        ObjectNode readLink = mapper.createObjectNode();
        ObjectNode readOutput = mapper.createObjectNode();

        ArrayNode outputSchema = mapper.createArrayNode();
        ObjectNode stateField = mapper.createObjectNode();
        stateField.put("name", "value");
        stateField.set("schema", mapper.createObjectNode().put("type", "boolean"));
        ObjectNode dateTimeField = mapper.createObjectNode();
        dateTimeField.put("name", "timestamp");
        dateTimeField.set("schema", mapper.createObjectNode().put("type", "string"));
        outputSchema.add(stateField);
        outputSchema.add(dateTimeField);


        keywords.add("door");
        keywords.add("sensor");

        readLink.put("href", "/objects/" + device.getUuid() + "/properties/state");
        readOutput.put("type", "object");
        readOutput.put("description", "state of the door");
        readOutput.set("field", outputSchema);
        readLink.set("output", readOutput);

        props.add(doorState);
        doorState.put("pid", pid);
        doorState.put("monitors", monitors);
        doorState.set("read_link", readLink);

        return this.createThingDesc(device.getUuid(), device.getDeviceName(), deviceType, version, keywords, props);
    }

    private ObjectNode createThingDesc(UUID oid, String name, String type, String version, ArrayNode keywords, ArrayNode props){
        ObjectNode jn = mapper.createObjectNode();
        jn.put("oid", oid.toString());
        jn.put("name", name);
        jn.put("type", type);
        jn.put("version", version);
        jn.set("keywords", keywords);
        jn.set("properties", props);

        jn.set("actions", mapper.createArrayNode());
        ObjectNode output = (ObjectNode) jn.get("properties").get(0).get("read_link").get("output");
        ObjectNode eventDesc = mapper.createObjectNode();
        eventDesc.put("eid", "door_activity_"+oid.toString());
        eventDesc.put("monitors", "adapters:EntryExit");
        eventDesc.set("output", output);
        jn.set("events", mapper.createArrayNode().add(eventDesc));
        return jn;
    }

    public JsonNode getThingDescWrapper(String adapterID){
        return mapper.createObjectNode().put("adapter-id", adapterID).set("thing-descriptions", mapper.createArrayNode());
    }
}
