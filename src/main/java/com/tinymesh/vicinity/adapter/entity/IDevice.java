package com.tinymesh.vicinity.adapter.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IDevice {
    String getDeviceName();

    void setDeviceName(String deviceName);

    Boolean isState();

    void setState(Boolean state);

    UUID getUuid();

    void setUuid(UUID uuid);

    String getDeviceType();

    void setDeviceType(String deviceType);

    String getUrl();

    void setUrl(String url);

    LocalDateTime getDateTime();

    void setDateTime(LocalDateTime dateTime);

    String toString();
}
