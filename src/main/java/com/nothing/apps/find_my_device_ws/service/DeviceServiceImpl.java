package com.nothing.apps.find_my_device_ws.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.nothing.apps.find_my_device_ws.dao.DeviceRepository;
import com.nothing.apps.find_my_device_ws.dao.OwnerRepository;
import com.nothing.apps.find_my_device_ws.db.Device;
import com.nothing.apps.find_my_device_ws.db.Owner;
import com.nothing.apps.find_my_device_ws.dto.SaveDeviceRequestDto;
import com.nothing.apps.find_my_device_ws.exception.InvalidMobileNumberException;
import com.nothing.apps.find_my_device_ws.exception.OwnerAlreadyExistException;
import com.nothing.apps.find_my_device_ws.exception.OwnerDoesNotExistException;
import com.nothing.apps.find_my_device_ws.exception.SaveDeviceException;
import com.nothing.apps.find_my_device_ws.utils.InvalidEmailAddressException;
import com.nothing.apps.find_my_device_ws.utils.RandomGenerator;
import com.nothing.apps.find_my_device_ws.utils.StringUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	DeviceRepository deviceRepo;

	@Autowired
	OwnerRepository ownerRepo;

	@Override
	public Device saveDevice(SaveDeviceRequestDto device) throws SaveDeviceException, OwnerAlreadyExistException {

		log.info("inside saveDevice");

		Device theDevice;

		if (device.getOwnerId() != null) {

//			log.info("checking if user exists anywhere");
//
//			doesUserExistAnyWhere(device.getOwnerMobileNumber());
//			doesUserExistAnyWhere(device.getOwnerEmailAddress());

		}

		if (device.getOwnerId() == null) {

			log.info("checking if user exists anywhere");

			doesUserExistAnyWhere(device.getOwnerMobileNumber());
			doesUserExistAnyWhere(device.getOwnerEmailAddress());

		}

		theDevice = new Device();
		Owner theOwner = new Owner();
		theDevice.setDeviceModel(device.getDeviceModel());
		theDevice.setDeviceId(UUID.randomUUID());
		theDevice.setDeviceType(device.getDeviceType());
		theDevice.setHardwareAddress(device.getHardwareAddress());
		theDevice.setSerialNumber(device.getSerialNumber());
		theOwner.setEmailAddress(device.getOwnerEmailAddress());
		theOwner.setMobileNumber(device.getOwnerMobileNumber());
		String ownerId = "";
		if (device.getOwnerId() == null) {
			log.info("user wants to register {}", ownerId);
			ownerId = device.getOwnerEmailAddress().split("@")[0];
			log.info("system generated ownerId {}", ownerId);
			theDevice.setOwnerId(ownerId);
		} else {
			log.info("user wants to save devices {}", ownerId);
			ownerId = device.getOwnerId();
			theDevice.setOwnerId(ownerId);

		}

		// Email Address Parameter
		Boolean onEmailChannel = null;
		try {

			if (StringUtil.isEmail(theOwner.getEmailAddress())) {

				onEmailChannel = !hasOwnerSavedDeviceBeforeWithEmail(theOwner.getEmailAddress());
			}

		} catch (Exception e) {

			System.err.println("onEmailChannel :: InvalidEmailAddressException");
		}

		if (onEmailChannel) {

			log.info("email channel");
			log.info("new user detected!");
			log.info("save device...");

			theOwner.setOwnerId(ownerId);
			theOwner.setFirstName(null);
			theOwner.setLastName(null);
			theOwner.setCountry(null);
			theOwner.setCountryCode(null);
			theOwner.setOwnerNo(Long.valueOf(RandomGenerator.produceRandomNumber(6)));
			theOwner.setOwnerRefNo(UUID.randomUUID());
			theDevice = deviceRepo.save(theDevice);
			log.info("The device {}", theDevice);
			log.info("create owner...");
			theOwner = ownerRepo.save(theOwner);

			log.info("The owner {}", theOwner);
		} else {

			log.info("existing user.");
			log.info("save device only...");
			theDevice = deviceRepo.save(theDevice);
		}
		// MobileNumber Parameter
		Boolean onMobileNumberChannel = null;
		try {
			if (StringUtil.isMobileNumber(theOwner.getMobileNumber())) {

				onMobileNumberChannel = !hasOwnerSavedDeviceBeforeWithMobileNumber(theOwner.getMobileNumber());
			}

		} catch (Exception e) {
			throw new SaveDeviceException(e);
		}

		if (onMobileNumberChannel) {
			log.info("mobileNumber channel");

			log.info("new user detected!");
			log.info("save device...");

			theOwner.setOwnerId(ownerId);
			theOwner.setFirstName(null);
			theOwner.setLastName(null);
			theOwner.setCountry(null);
			theOwner.setCountryCode(null);
			theOwner.setOwnerNo(Long.valueOf(RandomGenerator.produceRandomNumber(6)));
			theOwner.setOwnerRefNo(UUID.randomUUID());
			theOwner.setMobileNumber(device.getOwnerMobileNumber());
			theDevice = deviceRepo.save(theDevice);
			log.info("The device {}", theDevice);
			log.info("create owner...");
			theOwner = ownerRepo.save(theOwner);

			log.info("The owner {}", theOwner);
		} else {

			log.info("existing user.");
			log.info("save device only...");
			theDevice = deviceRepo.save(theDevice);
		}

		return theDevice;

	}

	void doesUserExistAnyWhere(String needle) throws OwnerAlreadyExistException {

		log.info("inside  doesUserExistAnyWhere");

		findOwnerByEmailAddress(needle);
		findOwnerByMobileNumber(needle);

//		 Owner theOwner = ownerRepo.findOwnerByOwnerId(needle);
//		 
//		 if(theOwner == null) {
//			 throw new OwnerAlreadyExistException("a user already exist");
//		 }

	}

	private Boolean hasOwnerSavedDeviceBeforeWithEmail(String arg) {

		log.info("checking hasOwnerSavedDeviceBeforeWithEmail {} ", arg);
		Boolean result = false;

//		if (StringUtil.isEmail(arg)) {
		try {

			log.info("isEmail");
			Owner theOwner = findOwnerByEmailAddress(arg);
			log.info("theOwner inside hasOwnerSavedDevice before {}", theOwner);
		} catch (OwnerAlreadyExistException e) {

			log.info("yes because owner already exist");

			result = true;

		}
//		}
		return result;

	}

	public Boolean hasOwnerSavedDeviceBeforeWithMobileNumber(String arg) {

		log.info("checking hasOwnerSavedDeviceBeforeWithMobileNumber {} ", arg);

		Boolean result = false;

//		if (StringUtil.isMobileNumber(arg)) {
		try {
			log.info("isMobile");
			Owner theOwner = findOwnerByMobileNumber(arg);
			log.info("theOwner inside hasOwnerSavedDevice before {}", theOwner);
		} catch (OwnerAlreadyExistException e) {

			log.info("yes because owner already exist");

			result = true;

		}
//		}

		return result;

	}

	@Override
	public List<Device> fetchDeviceByOwnerEmailAddress(String emailAddress) throws OwnerDoesNotExistException {

		log.info("inside fetchDeviceByOwnerEmailAddress");

		log.info("fetching owner's device ");

		Owner theOwner = null;
		List<Device> theDevices;
		theOwner = ownerRepo.findOwnerByEmailAddress(emailAddress);

		if (theOwner == null) {

			throw new OwnerDoesNotExistException("owner does not exist!");
		} else {

			theDevices = deviceRepo.findDeviceByOwnerId(theOwner.getOwnerId());

		}

		return theDevices;
	}

	@Override
	public Owner findOwnerByEmailAddress(String emailAddress) throws OwnerAlreadyExistException {

		log.info("@@@ inside  findOwnerByEmailAddress");

		log.info("@@@ finding owner by email address {}", emailAddress);
		Owner theOwner = ownerRepo.findOwnerByEmailAddress(emailAddress);

		log.info("The owner {}", theOwner);

		if (theOwner != null) {

			throw new OwnerAlreadyExistException("a user already exist with this emailAddress");
		}

		return theOwner;

	}

	@Override
	public Owner findOwnerByMobileNumber(String mobileNumber) throws OwnerAlreadyExistException {
		log.info("@@@ inside  findOwnerByMobileNumber");

		log.info("@@@ finding owner by mobile number");
		Owner theOwner = ownerRepo.findOwnerByMobileNumber(mobileNumber);

		if (theOwner != null) {

			throw new OwnerAlreadyExistException("a user already exist with this mobileNumber");
		}

		return theOwner;
	}

}
