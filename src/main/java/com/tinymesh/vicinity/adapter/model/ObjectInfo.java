package com.tinymesh.vicinity.adapter.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.tinymesh.vicinity.adapter.model.ObjectAction;
import com.tinymesh.vicinity.adapter.model.ObjectEvent;
import com.tinymesh.vicinity.adapter.model.ObjectProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ObjectInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-12T13:15:43.539Z")

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
  @ApiModelProperty(example = "Thermostate", required = true, value = "Type of IoT objects is a class from VICINITY core ontology, see http://iot.linkeddata.es/def/core/index-en.html, figure 2 hierarchy of devices, with \"core:\" prefix removed")
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
  @ApiModelProperty(example = "0729a580-2240-11e6-9eb5-0002a5d5c51b", required = true, value = "infrastructure specific identifier of IoT object")
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
  @ApiModelProperty(example = "my device", required = true, value = "human readable name of IoT object")
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
  @ApiModelProperty(required = true, value = "")
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
  @ApiModelProperty(required = true, value = "")
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
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<ObjectEvent> getEvents() {
    return events;
  }

  public void setEvents(List<ObjectEvent> events) {
    this.events = events;
  }


  @Override
  public boolean equals(Object o) {
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
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

