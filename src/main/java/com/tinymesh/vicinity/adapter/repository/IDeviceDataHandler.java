package com.tinymesh.vicinity.adapter.repository;

import com.tinymesh.vicinity.adapter.entity.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IDeviceDataHandler extends CrudRepository<Device, UUID> {

}
