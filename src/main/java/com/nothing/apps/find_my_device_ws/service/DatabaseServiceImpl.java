package com.nothing.apps.find_my_device_ws.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nothing.apps.find_my_device_ws.dao.DeviceRepository;
import com.nothing.apps.find_my_device_ws.dao.OwnerRepository;
import com.nothing.apps.find_my_device_ws.db.Device;
import com.nothing.apps.find_my_device_ws.db.Owner;
import com.nothing.apps.find_my_device_ws.dto.DeviceForm;
import com.nothing.apps.find_my_device_ws.dto.OwnerDetailsFormDto;
import com.nothing.apps.find_my_device_ws.dto.SaveDeviceRequestDto;
import com.nothing.apps.find_my_device_ws.exception.DuplicateHardwareAddressException;
import com.nothing.apps.find_my_device_ws.exception.OwnerAlreadyExistException;
import com.nothing.apps.find_my_device_ws.exception.OwnerDoesNotExistException;
import com.nothing.apps.find_my_device_ws.exception.SaveDeviceException;
import com.nothing.apps.find_my_device_ws.utils.RandomGenerator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DatabaseServiceImpl implements DatabaseService {

	@Autowired
	DeviceRepository deviceRepo;

	@Autowired
	OwnerRepository ownerRepo;

	@Override
	public List<Device> saveDevice(SaveDeviceRequestDto request) throws SaveDeviceException {

		Set<DeviceForm> deviceList = request.getDeviceList();

		List<Device> list = new ArrayList<>();

		try {

			for (DeviceForm form : deviceList) {

				if (deviceRepo.findDeviceByHardwareAddress(form.getHardwareAddress()) != null) {

					Throwable uniqueViolationCause = new Throwable("device hardware address, {} , is a duplicate".replace("{}", form.getHardwareAddress()));
					throw new DuplicateHardwareAddressException(uniqueViolationCause.getMessage());
				}

				
				Device tempDevice = deviceRepo.save(new Device(null, UUID.randomUUID(), form.getSerialNumber(),
						form.getDeviceType(), form.getDeviceModel(), request.getOwnerId(), form.getHardwareAddress()));

				list.add(tempDevice);

			}

		} catch (

		Exception e) {

			String text = "your  device could not be saved";
			String message = list.size() < 1 ? text : text.replace("device", "devices");

			throw new SaveDeviceException(message, e);
		}

		return list;
	}

	@Override
	public Owner onboardOwner(OwnerDetailsFormDto data) throws OwnerAlreadyExistException {

		log.info("inside onboardOwner");

		log.info("onboard onwer with formData {}", data);

		log.info("does owner exist?");
		Owner theOwner;
		log.info("checking with email ?");
		theOwner = ownerRepo.findOwnerByEmailAddress(data.getEmailAddress());
		if (theOwner != null) {
			log.info("email :: true");
			throw new OwnerAlreadyExistException(
					"a user already exist with the email, {}".replace("{}", data.getEmailAddress()));
		} else {
			log.info("email :: false");
		}

		log.info("checking with mobileNumber ?");
		theOwner = ownerRepo.findOwnerByMobileNumber(data.getMobileNumber());
		if (theOwner != null) {
			log.info("mobileNumber :: true");
			throw new OwnerAlreadyExistException(
					"a user already exist with the mobileNumber, {}".replace("{}", data.getMobileNumber()));
		} else {
			log.info("mobileNumber :: false");
		}

		log.info(":) , new user detected!");

		theOwner = new Owner();
		theOwner.setCountry(data.getCountry());
		theOwner.setFirstName(data.getFirstName());
		theOwner.setLastName(data.getLastName());
		theOwner.setMobileNumber(data.getMobileNumber());
		theOwner.setOwnerId(data.getEmailAddress().split("@")[0]);
		theOwner.setEmailAddress(data.getEmailAddress());
		theOwner.setOwnerRefNo(UUID.randomUUID());
		theOwner.setOwnerNo(Long.valueOf(RandomGenerator.produceRandomNumber(6)));
		theOwner.setCountryCode(data.getCountryCode());

		theOwner = ownerRepo.save(theOwner);

		return theOwner;
	}

	@Override
	public List<Device> fetchDeviceByOwnerId(String OwnerId) throws OwnerDoesNotExistException {

		log.info("inside fetchDeviceByOwnerId");

		log.info("fetching owner's device ");

		Owner theOwner = null;
		List<Device> deviceList;
		theOwner = ownerRepo.findOwnerByOwnerId(OwnerId);

		if (theOwner == null) {

			throw new OwnerDoesNotExistException("owner does not exist!");
		} else {

			deviceList = deviceRepo.findDeviceByOwnerId(theOwner.getOwnerId());

		}

		return deviceList;

	}

}
