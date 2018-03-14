package com.tinymesh.vicinity.adapter.service.impl;

import com.tinymesh.vicinity.adapter.dao.DeviceDao;
import com.tinymesh.vicinity.adapter.model.Device;
import com.tinymesh.vicinity.adapter.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DeviceServiceImpl implements DeviceService {


    @Autowired
    DeviceDao deviceDao;


    @Override
    public void insert(Device device) {
        deviceDao.insert(device);
    }

    @Override
    public void insertBatch(List<Device> devices) {

    }

    @Override
    public List<Device> loadAllDevice() {
        return null;
    }

    @Override
    public Device findDeviceByType(String deviceType) {
        return null;
    }

    @Override
    public String findDevByType(String deviceType) {
        return null;
    }

    @Override
    public int getTotalNumberDevice() {
        return 0;
    }
}
