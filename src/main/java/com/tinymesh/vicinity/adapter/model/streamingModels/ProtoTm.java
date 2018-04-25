package com.tinymesh.vicinity.adapter.model.streamingModels;

/**
 * Created by JacksonGenerator on 4/13/18.
 */

import com.fasterxml.jackson.annotation.JsonProperty;


public class ProtoTm {
    @JsonProperty("temp")
    private Integer temp;
    @JsonProperty("rssi")
    private Integer rssi;
    @JsonProperty("data")
    private Integer data;
    @JsonProperty("aio0")
    private Integer aio0;
    @JsonProperty("latency")
    private Integer latency;
    @JsonProperty("aio1")
    private Integer aio1;
    @JsonProperty("network_lvl")
    private Integer networkLvl;
    @JsonProperty("dio")
    private Dio dio;
    @JsonProperty("type")
    private String type;
    @JsonProperty("sid")
    private Integer sid;
    @JsonProperty("hw")
    private String hw;
    @JsonProperty("uid")
    private Long uid;
    @JsonProperty("fw")
    private String fw;
    @JsonProperty("volt")
    private Double volt;
    @JsonProperty("packet_number")
    private Integer packetNumber;
    @JsonProperty("hops")
    private Integer hops;
    @JsonProperty("detail")
    private String detail;
    @JsonProperty("locator")
    private Integer locator;

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getRssi() {
        return rssi;
    }

    public void setRssi(Integer rssi) {
        this.rssi = rssi;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public Integer getAio0() {
        return aio0;
    }

    public void setAio0(Integer aio0) {
        this.aio0 = aio0;
    }

    public Integer getLatency() {
        return latency;
    }

    public void setLatency(Integer latency) {
        this.latency = latency;
    }

    public Integer getAio1() {
        return aio1;
    }

    public void setAio1(Integer aio1) {
        this.aio1 = aio1;
    }

    public Integer getNetworkLvl() {
        return networkLvl;
    }

    public void setNetworkLvl(Integer networkLvl) {
        this.networkLvl = networkLvl;
    }

    public Dio getDio() {
        return dio;
    }

    public void setDio(Dio dio) {
        this.dio = dio;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getHw() {
        return hw;
    }

    public void setHw(String hw) {
        this.hw = hw;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getFw() {
        return fw;
    }

    public void setFw(String fw) {
        this.fw = fw;
    }

    public Double getVolt() {
        return volt;
    }

    public void setVolt(Double volt) {
        this.volt = volt;
    }

    public Integer getPacketNumber() {
        return packetNumber;
    }

    public void setPacketNumber(Integer packetNumber) {
        this.packetNumber = packetNumber;
    }

    public Integer getHops() {
        return hops;
    }

    public void setHops(Integer hops) {
        this.hops = hops;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getLocator() {
        return locator;
    }

    public void setLocator(Integer locator) {
        this.locator = locator;
    }
}