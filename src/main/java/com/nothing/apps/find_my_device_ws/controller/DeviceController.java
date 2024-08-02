package com.nothing.apps.find_my_device_ws.controller;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.time.OffsetDateTime;
import java.util.Enumeration;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nothing.apps.find_my_device_ws.db.Device;
import com.nothing.apps.find_my_device_ws.dto.SaveDeviceRequestDto;
import com.nothing.apps.find_my_device_ws.exception.InvalidMobileNumberException;
import com.nothing.apps.find_my_device_ws.exception.OwnerAlreadyExistException;
import com.nothing.apps.find_my_device_ws.exception.OwnerDoesNotExistException;
import com.nothing.apps.find_my_device_ws.exception.SaveDeviceException;
import com.nothing.apps.find_my_device_ws.response.RootResponse;
import com.nothing.apps.find_my_device_ws.service.DeviceService;
import com.nothing.apps.find_my_device_ws.utils.InvalidEmailAddressException;
import com.nothing.apps.find_my_device_ws.utils.StringUtil;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api")
@RestController
@Slf4j
public class DeviceController {

	@GetMapping("/ping")
	String ping() {
		return "device-controller";
	}

	@Autowired
	private DeviceService deviceService;

	@PostMapping("/devices")
	public ResponseEntity<RootResponse<Device>> saveDevice(@RequestBody SaveDeviceRequestDto requestDto)
			throws SaveDeviceException, InvalidEmailAddressException, InvalidMobileNumberException, OwnerAlreadyExistException {

		log.info("inside saveDevice");

		log.info("saveDevice request :: ", new JSONObject(requestDto));

		log.info("@@@ going to save the owner's device");

		try {
			
			
		
				if (StringUtil.isMobileNumber(requestDto.getOwnerMobileNumber())) {

					log.info("MobileNumber supplied is valid");
					log.info("proceed to saveDevice");
				}

				if (StringUtil.isEmail(requestDto.getOwnerEmailAddress())) {
					log.info("EmailAddress supplied is valid");
					log.info("proceed to saveDevice");
				}
			
			
	

		} catch ( Exception e) {
			
			throw new SaveDeviceException("device could not be saved..",e);
		}
		
		
		Device theDevice = deviceService.saveDevice(requestDto);

		RootResponse<Device> apiResponse = new RootResponse<>();
		apiResponse.setCode(HttpStatus.CREATED.value());
		apiResponse.setResponse(theDevice);
		apiResponse.setStatus("success");
		apiResponse.setMessage("your device was successfully saved!");
		apiResponse.setTimeStamp(OffsetDateTime.now());

		return new ResponseEntity<RootResponse<Device>>(apiResponse, HttpStatus.CREATED);

	}

	@GetMapping("/owners/{emailAddress}/devices")
	public ResponseEntity<RootResponse<List<Device>>> getDeviceByOwnerEmailAddress(
			@PathVariable(name = "emailAddress") String emailAddress) throws OwnerDoesNotExistException {

		log.info("inside getDeviceByOwnerEmailAddress");

		log.info("emailAddress  :: {} ", emailAddress);

		log.info("@@@ going to fetch the owner's device");
		List<Device> theDevices = deviceService.fetchDeviceByOwnerEmailAddress(emailAddress);

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

	public static void main(String[] args) {

		Enumeration<NetworkInterface> interfaceEnums;
		try {

			interfaceEnums = NetworkInterface.getNetworkInterfaces();
			for (Enumeration<NetworkInterface> e = interfaceEnums; e.hasMoreElements();) {
				System.out.println("The network interface name : " + e.nextElement().getDisplayName());

				String macAddress = null;

				try {

					byte[] hardwareAddr = e.nextElement().getHardwareAddress();

					String[] hexadecimal = new String[hardwareAddr.length];
					for (int i = 0; i < hardwareAddr.length; i++) {
						hexadecimal[i] = String.format("%02X", hardwareAddr[i]); // hexadecimal
					}

					macAddress = String.join("-", hexadecimal);

					System.out.println("The network interface hardware address : " + macAddress);

				} catch (Exception ex) {

					macAddress = "not available";
					System.err.println("The network interface hardware address : " + macAddress);

				}

			}

		} catch (SocketException e1) {
			e1.printStackTrace();
		}

	}

}
