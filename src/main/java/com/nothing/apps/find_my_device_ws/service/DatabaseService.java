package com.nothing.apps.find_my_device_ws.service;

import java.util.List;

import com.nothing.apps.find_my_device_ws.db.Device;
import com.nothing.apps.find_my_device_ws.db.Owner;
import com.nothing.apps.find_my_device_ws.dto.OwnerDetailsFormDto;
import com.nothing.apps.find_my_device_ws.dto.SaveDeviceRequestDto;
import com.nothing.apps.find_my_device_ws.exception.InvalidEmailAddressException;
import com.nothing.apps.find_my_device_ws.exception.InvalidMobileNumberException;
import com.nothing.apps.find_my_device_ws.exception.OwnerAlreadyExistException;
import com.nothing.apps.find_my_device_ws.exception.OwnerDoesNotExistException;
import com.nothing.apps.find_my_device_ws.exception.SaveDeviceException;

public interface DatabaseService {
	
	List<Device> saveDevice(SaveDeviceRequestDto device) throws SaveDeviceException;

	Owner onboardOwner(OwnerDetailsFormDto data) throws OwnerAlreadyExistException, InvalidEmailAddressException;

	List<Device> fetchDeviceByOwnerId(String OwnerId) throws OwnerDoesNotExistException;


}
