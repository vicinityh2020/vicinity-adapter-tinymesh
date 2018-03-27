package com.tinymesh.vicinity.adapter.database;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IDeviceUtilization {

    UUID getUuid();

    void setUuid(UUID uuid);

    LocalDateTime getOpened();

    void setOpened(LocalDateTime opened);

    LocalDateTime getClosed();

    void setClosed(LocalDateTime closed);

    int getUtilization();

    void setUtilization(int utilization);

    UUID getDeviceUUID();

    void setDeviceUUID(UUID deviceUUID);

    String toString();
}
