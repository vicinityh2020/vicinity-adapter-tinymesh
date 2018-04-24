package com.tinymesh.vicinity.adapter.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;


@Entity
@Table(name = "device")
public class Device {


    private Boolean state;
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private UUID uuid;

    @Column(name = "devicename")
    private String deviceName;
    @Column(name = "devicetype")
    private String deviceType;
    @Column(name = "datetime")
    private LocalDateTime dateTime;
    @Column(name = "url")
    private String url;
    @Column(name = "tinymuid", nullable = false)
    private long tinyMuid;

    /**
     * @param deviceName name of Device.
     * @param deviceType type of Device. for example: "sensor".
     * @param uuid Universally unique ID of Device.
     * @param dateTime Date and time.
     * @param state boolean state of Device.
     * @param url URL address of Device.
     * @param tinyMuid ID we get from TinyMesh Cloud.
     */
    public Device(String deviceName, String deviceType, UUID uuid, LocalDateTime dateTime, Boolean state, String url, long tinyMuid) {
        this.deviceName = deviceName;
        this.uuid = uuid;
        this.dateTime = dateTime;
        this.deviceType = deviceType;
        this.state = state;
        this.url = url;
        this.tinyMuid = tinyMuid;
    }

    /**
     * Empty constructor.
     */
    public Device() {
    }

    /**
     * Gets name of Device.
     * @return deviceName
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * Setting name for Device.
     * @param deviceName name of Device.
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * Gets if state is false or true.
     * @return state
     */
    public Boolean isState() {
        return state;
    }

    /**
     * Sets state false or true. It can be NULL
     * @param state boolean state of Device.
     */
    public void setState(Boolean state) {
        this.state = state;
    }

    /**
     * Gets UUID of Device.
     * @return uuid
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Sets UUID to Device.
     * @param uuid Universally unique ID of Device.
     */
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Gets Device type.
     * @return
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * Sets type to Device.
     * @param deviceType type of Device. for example: "sensor".
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * Gets URL address of Device.
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets URL address to Device.
     * @param url URL address of Device.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets Date and Time of Device.
     * @return
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Sets Date and Time to Device
     * @param dateTime Date and time.
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Gets ID from TinyMesh Cloud
     * @return
     */
    public long getTinyMuid() {
        return tinyMuid;
    }

    /**
     * Sets ID to TinyMesh Cloud
     * @param tinyMuid ID of TinyMesh Cloud.
     */
    public void setTinyMuid(long tinyMuid) {
        this.tinyMuid = tinyMuid;
    }

    /**
     * Time zone should be UTC by default. Makes sure that everything is consistent explicitly declare as UTC
     * @param state boolean state of Device.
     * @param lastUpdateDateTime Last updated Date and Time of Device.
     */
    public void updateDeviceState(boolean state, String lastUpdateDateTime){

        LocalDateTime time = LocalDateTime.ofInstant(Instant.parse(lastUpdateDateTime), ZoneId.of("UTC"));
        this.setDateTime(time);
        this.setState(state);
    }

    /**
     *
     * @return "Device [DeviceType=" + deviceType +
    ", uuid =" + uuid +
    ", Date=" + dateTime +
    ", State=" + state +
    ", URL=" + url + "]"
     */
    public String toString() {
        return "Device [DeviceType=" + deviceType +
                ", uuid =" + uuid +
                ", Date=" + dateTime +
                ", State=" + state +
                ", URL=" + url + "]";
    }
}
