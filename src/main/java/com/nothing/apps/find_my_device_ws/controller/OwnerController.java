package com.nothing.apps.find_my_device_ws.controller;

import java.time.OffsetDateTime;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nothing.apps.find_my_device_ws.db.Device;
import com.nothing.apps.find_my_device_ws.db.Owner;
import com.nothing.apps.find_my_device_ws.dto.OwnerDetailsFormDto;
import com.nothing.apps.find_my_device_ws.exception.InvalidEmailAddressException;
import com.nothing.apps.find_my_device_ws.exception.InvalidMobileNumberException;
import com.nothing.apps.find_my_device_ws.exception.OwnerAlreadyExistException;
import com.nothing.apps.find_my_device_ws.exception.OwnerDoesNotExistException;
import com.nothing.apps.find_my_device_ws.response.RootResponse;
import com.nothing.apps.find_my_device_ws.service.DatabaseService;
import com.nothing.apps.find_my_device_ws.utils.StringUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OwnerController {

	@Autowired
	private DatabaseService dbService;

	@PostMapping("/onboarding/owners")
	ResponseEntity<RootResponse<Owner>> onboardOwner(@RequestBody OwnerDetailsFormDto formDto)
			throws InvalidMobileNumberException, InvalidEmailAddressException, OwnerAlreadyExistException {

		log.info("@@ incoming ownerDetails Data {}", new JSONObject(formDto));

		String mobileNumber = StringUtil.isMobileNumber(formDto.getMobileNumber()) ? formDto.getMobileNumber() : null;
		String emailAddress = StringUtil.isEmail(formDto.getEmailAddress()) ? formDto.getEmailAddress() : null;

		formDto.setEmailAddress(emailAddress);
		formDto.setMobileNumber(mobileNumber);

		Owner theOwner = dbService.onboardOwner(formDto);

		RootResponse<Owner> apiResponse = new RootResponse<>();
		apiResponse.setCode(HttpStatus.CREATED.value());
		apiResponse.setResponse(theOwner);
		apiResponse.setStatus("success");
		apiResponse.setMessage("you have successfully onboarded!");
		apiResponse.setTimeStamp(OffsetDateTime.now());

		return new ResponseEntity<RootResponse<Owner>>(apiResponse, HttpStatus.CREATED);
		
	}

	@GetMapping("/owners/{ownerId}/devices")
	public ResponseEntity<RootResponse<List<Device>>> getDeviceByOwnerId(@PathVariable(name = "ownerId") String ownerId)
			throws OwnerDoesNotExistException {

		log.info("inside getDeviceByOwnerId");

		log.info("ownerId  :: {} ", ownerId);

		log.info("@@@ going to fetch the owner's device(s)");
		List<Device> theDevices = dbService.fetchDeviceByOwnerId(ownerId);

		RootResponse<List<Device>> apiResponse = new RootResponse<>();
		apiResponse.setCode(HttpStatus.OK.value());
		apiResponse.setResponse(theDevices);
		apiResponse.setStatus("success");
		String text = "your device was found";
		String message = theDevices.size() < 1 ? text : text.replace("device", "devices").replace("was", "were");
		apiResponse.setMessage(message);
		apiResponse.setTimeStamp(OffsetDateTime.now());

		return new ResponseEntity<RootResponse<List<Device>>>(apiResponse, HttpStatus.OK);

	}

}
