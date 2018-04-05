package com.tinymesh.vicinity.adapter.jsonmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class DoorSensor {
    private LocalDateTime recieved;
    private LocalDateTime datetime;
    private UUID key;

    @JsonProperty("proto/tm")
    private DeviceProperties deviceProperties;
    private int network_lvl;
    private long sid;
    private long uid;
    private String detail;

    public LocalDateTime getRecieved() {
        return recieved;
    }

    public void setRecieved(LocalDateTime recieved) {
        this.recieved = recieved;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public UUID getKey() {
        return key;
    }

    public void setKey(UUID key) {
        this.key = key;
    }

    public DeviceProperties getDeviceProperties() {
        return deviceProperties;
    }

    public void setDeviceProperties(DeviceProperties deviceProperties) {
        this.deviceProperties = deviceProperties;
    }

    public int getNetwork_lvl() {
        return network_lvl;
    }

    public void setNetwork_lvl(int network_lvl) {
        this.network_lvl = network_lvl;
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}

