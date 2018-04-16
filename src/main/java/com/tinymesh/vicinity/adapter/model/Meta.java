package com.tinymesh.vicinity.adapter.model;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "updated",
    "event/date",
    "created",
    "event/key"
})
public class Meta {

    @JsonProperty("updated")
    private String updated;
    @JsonProperty("event/date")
    private String eventDate;
    @JsonProperty("created")
    private String created;
    @JsonProperty("event/key")
    private String eventKey;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("updated")
    public String getUpdated() {
        return updated;
    }

    @JsonProperty("updated")
    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @JsonProperty("event/date")
    public String getEventDate() {
        return eventDate;
    }

    @JsonProperty("event/date")
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    @JsonProperty("created")
    public String getCreated() {
        return created;
    }

    @JsonProperty("created")
    public void setCreated(String created) {
        this.created = created;
    }

    @JsonProperty("event/key")
    public String getEventKey() {
        return eventKey;
    }

    @JsonProperty("event/key")
    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
