package com.tinymesh.vicinity.adapter.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "deviceutilization")
public class DeviceUtilization {

    //    @Id
//    @GeneratedValue(generator = "system-uui")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    /*(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")*/
    @Id
    @GeneratedValue
    @Column(name = "uuid", unique = true)
    private UUID uuid;

    private LocalDateTime opened;
    private LocalDateTime closed;
    private int utilization;


    @ManyToOne(targetEntity = Device.class)
    private Device deviceUUID;

    /**
     * @param uuid        unique ID of DeviceUtil
     * @param opened      when Door is opened
     * @param closed      when door is closed
     * @param utilization utilization of Device
     * @param deviceUUID  unique ID of Device
     * @see Device
     */
    //UUID uuid,
    public DeviceUtilization(LocalDateTime opened, LocalDateTime closed, int utilization, Device deviceUUID) {
//        this.uuid = uuid;
        this.opened = opened;
        this.closed = closed;
        this.utilization = utilization;
        this.deviceUUID = deviceUUID;
    }

    /**
     * Empty constructor.
     */
    public DeviceUtilization() {

    }

    /**
     * Gets UUID
     *
     * @return uuid
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Sets UUID
     *
     * @param uuid unique ID of DeviceUtil
     */
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Gets date and time door was opened
     *
     * @return opened
     */
    public LocalDateTime getOpened() {
        return opened;
    }

    /**
     * Sets date and time door was opened
     *
     * @param opened when Door is opened
     */
    public void setOpened(LocalDateTime opened) {
        this.opened = opened;
    }

    /**
     * Gets date and time door was closed
     *
     * @return closed
     */
    public LocalDateTime getClosed() {
        return closed;
    }

    /**
     * Sets date and time door was closed
     *
     * @param closed when door is closed
     */
    public void setClosed(LocalDateTime closed) {
        this.closed = closed;
    }

    /**
     * Gets utilization
     *
     * @return utilization
     */
    public int getUtilization() {
        return utilization;
    }

    /**
     * Sets utilization
     *
     * @param utilization utilization of Device
     */
    public void setUtilization(int utilization) {
        this.utilization = utilization;
    }

    /**
     * Gets UUID of Device
     *
     * @return deviceUUID
     */
    public Device getDeviceUUID() {
        return deviceUUID;
    }

    /**
     * Sets UUID of Device
     *
     * @param deviceUUID unique ID of Device
     */
    public void setDeviceUUID(Device deviceUUID) {
        this.deviceUUID = deviceUUID;
    }

    /**
     * @return "Device [UUID =" + uuid + ", opened =" + opened + ",
     * closed =" + closed + ", utilization =" + utilization + ",
     * Device UUID =" + deviceUUID + "]"
     */
    public String toString() {
        return "Device [UUID =" + uuid + ", opened =" + opened + ", closed =" + closed + ", utilization =" + utilization + ", Device UUID =" + deviceUUID + "]";
    }
}
