package com.tinymesh.vicinity.adapter.service;

import com.tinymesh.vicinity.adapter.model.Device;

import java.util.List;

public interface DeviceService {
    void insert(Device device);

    void insertBatch(List<Device> devices);

    List<Device> loadAllDevice();

    Device findDeviceByType(String deviceType);

    String findDevByType(String deviceType);

    int getTotalNumberDevice();
}
