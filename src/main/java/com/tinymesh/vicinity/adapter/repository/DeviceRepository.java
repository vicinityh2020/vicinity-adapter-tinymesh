package com.tinymesh.vicinity.adapter.repository;

import com.tinymesh.vicinity.adapter.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {
    Device findByTinyMuid(long uid);
}
