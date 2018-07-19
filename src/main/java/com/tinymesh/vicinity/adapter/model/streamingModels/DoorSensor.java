package com.tinymesh.vicinity.adapter.model.streamingModels;

/**
 * Created by JacksonGenerator on 4/13/18.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class DoorSensor {
    @JsonProperty("datetime")
    private String datetime;
    @JsonProperty("proto/tm")
    private ProtoTm protoTm;
    @JsonProperty("received")
    private String received;
    @JsonProperty("key")
    private String key;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public ProtoTm getProtoTm() {
        return protoTm;
    }

    public void setProtoTm(ProtoTm protoTm) {
        this.protoTm = protoTm;
    }

    public String getReceived() {
        return received;
    }

    public void setReceived(String received) {
        this.received = received;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "DoorSensor{" +
                "datetime='" + datetime + '\'' +
                ", protoTm=" + protoTm +
                ", received='" + received + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}