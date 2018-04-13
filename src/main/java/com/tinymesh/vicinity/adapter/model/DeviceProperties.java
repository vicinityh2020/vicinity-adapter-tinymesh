package com.tinymesh.vicinity.adapter.model;

public class DeviceProperties {
    private int aio1;
    private int aio0;
    private int rssi;
    private int packet_number;
    private int data;
    private int latency;
    private int hops;
    private String type;
    private String hardware;
    private double volt;
    private int temp;
    private String firmaware;
    private long locator;
    private DigitalIO dio;



    public int getAio1() {
        return aio1;
    }

    public void setAio1(int aio1) {
        this.aio1 = aio1;
    }

    public int getAio0() {
        return aio0;
    }

    public void setAio0(int aio0) {
        this.aio0 = aio0;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public int getPacket_number() {
        return packet_number;
    }

    public void setPacket_number(int packet_number) {
        this.packet_number = packet_number;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getLatency() {
        return latency;
    }

    public void setLatency(int latency) {
        this.latency = latency;
    }

    public int getHops() {
        return hops;
    }

    public void setHops(int hops) {
        this.hops = hops;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public double getVolt() {
        return volt;
    }

    public void setVolt(double volt) {
        this.volt = volt;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getFirmaware() {
        return firmaware;
    }

    public void setFirmaware(String firmaware) {
        this.firmaware = firmaware;
    }

    public long getLocator() {
        return locator;
    }

    public void setLocator(long locator) {
        this.locator = locator;
    }

    public DigitalIO getDio() {
        return dio;
    }

    public void setDio(DigitalIO dio) {
        this.dio = dio;
    }

    @Override
    public String toString() {
        return "DeviceProperties{" +
                "aio1=" + aio1 +
                ", aio0=" + aio0 +
                ", rssi=" + rssi +
                ", packet_number=" + packet_number +
                ", data=" + data +
                ", latency=" + latency +
                ", hops=" + hops +
                ", type='" + type + '\'' +
                ", hardware='" + hardware + '\'' +
                ", volt=" + volt +
                ", temp=" + temp +
                ", firmaware='" + firmaware + '\'' +
                ", locator=" + locator +
                ", dio=" + dio +
                '}';
    }
}
