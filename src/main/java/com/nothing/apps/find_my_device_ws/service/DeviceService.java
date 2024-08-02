package com.nothing.apps.find_my_device_ws.service;

import java.util.List;

import com.nothing.apps.find_my_device_ws.db.Device;
import com.nothing.apps.find_my_device_ws.db.Owner;
import com.nothing.apps.find_my_device_ws.dto.SaveDeviceRequestDto;
import com.nothing.apps.find_my_device_ws.exception.InvalidMobileNumberException;
import com.nothing.apps.find_my_device_ws.exception.OwnerAlreadyExistException;
import com.nothing.apps.find_my_device_ws.exception.OwnerDoesNotExistException;
import com.nothing.apps.find_my_device_ws.exception.SaveDeviceException;
import com.nothing.apps.find_my_device_ws.utils.InvalidEmailAddressException;

public interface DeviceService {
	
	Device saveDevice(SaveDeviceRequestDto device) throws SaveDeviceException, InvalidEmailAddressException, InvalidMobileNumberException, OwnerAlreadyExistException;

	List<Device> fetchDeviceByOwnerEmailAddress(String emailAddress) throws OwnerDoesNotExistException;
	
	Owner findOwnerByEmailAddress(String emailAddress) throws OwnerAlreadyExistException;
	
	Owner findOwnerByMobileNumber(String mobileNumber) throws OwnerAlreadyExistException;

}
