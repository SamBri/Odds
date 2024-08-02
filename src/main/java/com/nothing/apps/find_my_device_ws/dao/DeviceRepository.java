package com.nothing.apps.find_my_device_ws.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nothing.apps.find_my_device_ws.db.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

	List<Device> findDeviceByOwnerId(String ownerId);

}
