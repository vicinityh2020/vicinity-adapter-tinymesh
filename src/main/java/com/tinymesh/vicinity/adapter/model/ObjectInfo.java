package com.tinymesh.vicinity.adapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ObjectInfo   {
    @JsonProperty("type")
    private String type = null;

    @JsonProperty("oid")
    private UUID oid = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("properties")
    @Valid
    private List<ObjectProperty> properties = new ArrayList<ObjectProperty>();

    @JsonProperty("actions")
    @Valid
    private List<ObjectAction> actions = new ArrayList<ObjectAction>();

    @JsonProperty("events")
    @Valid
    private List<ObjectEvent> events = new ArrayList<ObjectEvent>();

    public ObjectInfo type(String type) {
        this.type = type;
        return this;
    }

    /**
     * Type of IoT objects is a class from VICINITY core ontology, see http://iot.linkeddata.es/def/core/index-en.html, figure 2 hierarchy of devices, with \"core:\" prefix removed
     * @return type
     **/
    @NotNull


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ObjectInfo oid(UUID oid) {
        this.oid = oid;
        return this;
    }

    /**
     * infrastructure specific identifier of IoT object
     * @return oid
     **/
    @NotNull
    @Valid
    public UUID getOid() {
        return oid;
    }

    public void setOid(UUID oid) {
        this.oid = oid;
    }

    public ObjectInfo name(String name) {
        this.name = name;
        return this;
    }

    /**
     * human readable name of IoT object
     * @return name
     **/
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObjectInfo properties(List<ObjectProperty> properties) {
        this.properties = properties;
        return this;
    }

    public ObjectInfo addPropertiesItem(ObjectProperty propertiesItem) {
        this.properties.add(propertiesItem);
        return this;
    }

    /**
     * Get properties
     * @return properties
     **/
    @NotNull
    @Valid
    public List<ObjectProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<ObjectProperty> properties) {
        this.properties = properties;
    }

    public ObjectInfo actions(List<ObjectAction> actions) {
        this.actions = actions;
        return this;
    }

    public ObjectInfo addActionsItem(ObjectAction actionsItem) {
        this.actions.add(actionsItem);
        return this;
    }

    /**
     * Get actions
     * @return actions
     **/
    @NotNull
    @Valid
    public List<ObjectAction> getActions() {
        return actions;
    }

    public void setActions(List<ObjectAction> actions) {
        this.actions = actions;
    }

    public ObjectInfo events(List<ObjectEvent> events) {
        this.events = events;
        return this;
    }

    public ObjectInfo addEventsItem(ObjectEvent eventsItem) {
        this.events.add(eventsItem);
        return this;
    }

    /**
     * Get events
     * @return events
     **/
    @NotNull
    @Valid
    public List<ObjectEvent> getEvents() {
        return events;
    }

    public void setEvents(List<ObjectEvent> events) {
        this.events = events;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ObjectInfo objectInfo = (ObjectInfo) o;
        return Objects.equals(this.type, objectInfo.type) &&
                Objects.equals(this.oid, objectInfo.oid) &&
                Objects.equals(this.name, objectInfo.name) &&
                Objects.equals(this.properties, objectInfo.properties) &&
                Objects.equals(this.actions, objectInfo.actions) &&
                Objects.equals(this.events, objectInfo.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, oid, name, properties, actions, events);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ObjectInfo {\n");

        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    oid: ").append(toIndentedString(oid)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
        sb.append("    actions: ").append(toIndentedString(actions)).append("\n");
        sb.append("    events: ").append(toIndentedString(events)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

