package com.tinymesh.vicinity.adapter.database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Embeddable
@Table(name = "device")
public class Device implements IDevice{

    private boolean state;
    @Id
    @GenericGenerator(name="system-uuid", strategy="uuid")
    private UUID uuid;

    private String deviceName;
    private String deviceType;
    private LocalDateTime dateTime;
    private String url;



    public Device(String deviceName, String deviceType, UUID uuid, LocalDateTime dateTime, boolean state, String url){
        this.deviceName = deviceName;
        this.uuid = uuid;
        this.dateTime = dateTime;
        this.deviceType = deviceType;
        this.state = state;
        this.url = url;
    }
    public Device(){}


    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public String toString(){
        return "Device [DeviceType=" + deviceType + ", uuid ="+ uuid + ", Date=" + dateTime + ", State=" + state + ", URL=" + url + "]";
    }
}
