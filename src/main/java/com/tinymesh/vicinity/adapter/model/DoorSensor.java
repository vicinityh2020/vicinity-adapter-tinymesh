package com.tinymesh.vicinity.adapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Arrays;

public class DoorSensor {
    private LocalDateTime recieved;
    private LocalDateTime datetime;
    private String key;

    @JsonProperty("proto/tm")
    private DeviceProperties deviceProperties;
    private int network_lvl;
    private long sid;
    private long uid;
    private String detail;
    private String network;
    private String type;
    private long address;
    private String name;
    private String provisioned;

    public String[] getLocation() {
        return location;
    }

    public void setLocation(String[] location) {
        this.location = location;
    }

    private String[] location;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
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

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getAddress() {
        return address;
    }

    public void setAddress(long address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvisioned() {
        return provisioned;
    }

    public void setProvisioned(String provisioned) {
        this.provisioned = provisioned;
    }

    @Override
    public String toString() {
        return "DoorSensor{" +
                "recieved=" + recieved +
                ", datetime=" + datetime +
                ", key='" + key + '\'' +
                ", deviceProperties=" + deviceProperties +
                ", network_lvl=" + network_lvl +
                ", sid=" + sid +
                ", uid=" + uid +
                ", detail='" + detail + '\'' +
                ", network='" + network + '\'' +
                ", type='" + type + '\'' +
                ", address=" + address +
                ", name='" + name + '\'' +
                ", provisioned='" + provisioned + '\'' +
                ", location=" + Arrays.toString(location) +
                '}';
    }
}

