package com.nothing.apps.find_my_device_ws.controller;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nothing.apps.find_my_device_ws.dto.SaveDeviceRequestDto;

@RequestMapping("/api")
@RestController
public class DeviceController {
	
	
	
	@PostMapping("/devices")
	public String saveDevice(@RequestBody SaveDeviceRequestDto requestDto) {
		
		
		
		return null;
		
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
