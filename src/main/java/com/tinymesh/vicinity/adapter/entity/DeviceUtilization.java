package com.tinymesh.vicinity.adapter.entity;

import com.tinymesh.vicinity.adapter.repository.IDeviceUtilization;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "deviceutilization")
public class DeviceUtilization implements IDeviceUtilization {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private UUID uuid;

    private LocalDateTime opened;
    private LocalDateTime closed;
    private int utilization;


    private UUID deviceUUID;

    public DeviceUtilization(UUID uuid, LocalDateTime opened, LocalDateTime closed, int utilization, UUID deviceUUID) {
        this.uuid = uuid;
        this.opened = opened;
        this.closed = closed;
        this.utilization = utilization;
        this.deviceUUID = deviceUUID;
    }

    public DeviceUtilization() {

    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getOpened() {
        return opened;
    }

    public void setOpened(LocalDateTime opened) {
        this.opened = opened;
    }

    public LocalDateTime getClosed() {
        return closed;
    }

    public void setClosed(LocalDateTime closed) {
        this.closed = closed;
    }

    public int getUtilization() {
        return utilization;
    }

    public void setUtilization(int utilization) {
        this.utilization = utilization;
    }

    public UUID getDeviceUUID() {
        return deviceUUID;
    }

    public void setDeviceUUID(UUID deviceUUID) {
        this.deviceUUID = deviceUUID;
    }

    public String toString() {
        return "Device [UUID =" + uuid + ", opened =" + opened + ", closed =" + closed + ", utilization =" + utilization + ", Device UUID =" + deviceUUID + "]";
    }
}
