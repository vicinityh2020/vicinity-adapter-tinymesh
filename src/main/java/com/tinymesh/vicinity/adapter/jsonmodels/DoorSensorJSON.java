
package com.tinymesh.vicinity.adapter.jsonmodels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "location",
    "proto/tm",
    "network",
    "type",
    "address",
    "name",
    "provisioned",
    "meta"
})
public class DoorSensorJSON {

    @JsonProperty("key")
    private String key;
    @JsonProperty("location")
    private List<Object> location = null;
    @JsonProperty("proto/tm")
    private ProtoTm protoTm;
    @JsonProperty("network")
    private String network;
    @JsonProperty("type")
    private String type;
    @JsonProperty("address")
    private Integer address;
    @JsonProperty("name")
    private String name;
    @JsonProperty("provisioned")
    private String provisioned;
    @JsonProperty("meta")
    private Meta meta;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
    }

    @JsonProperty("location")
    public List<Object> getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(List<Object> location) {
        this.location = location;
    }

    @JsonProperty("proto/tm")
    public ProtoTm getProtoTm() {
        return protoTm;
    }

    @JsonProperty("proto/tm")
    public void setProtoTm(ProtoTm protoTm) {
        this.protoTm = protoTm;
    }

    @JsonProperty("network")
    public String getNetwork() {
        return network;
    }

    @JsonProperty("network")
    public void setNetwork(String network) {
        this.network = network;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("address")
    public Integer getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Integer address) {
        this.address = address;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("provisioned")
    public String getProvisioned() {
        return provisioned;
    }

    @JsonProperty("provisioned")
    public void setProvisioned(String provisioned) {
        this.provisioned = provisioned;
    }

    @JsonProperty("meta")
    public Meta getMeta() {
        return meta;
    }

    @JsonProperty("meta")
    public void setMeta(Meta meta) {
        this.meta = meta;
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
