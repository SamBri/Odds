package com.nothing.apps.find_my_device_ws.controller;

import java.net.InterfaceAddress;
import java.net.NetPermission;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class DeviceController {

	public static void main(String[] args) {

		Enumeration<NetworkInterface> interfaceEnums;
		try {
			interfaceEnums = NetworkInterface.getNetworkInterfaces();
			for (Enumeration<NetworkInterface> e = interfaceEnums; e.hasMoreElements();) {
				System.out.println("The network interface name : " + e.nextElement().getDisplayName());
			System.out.println("The network interface hardware address : " + new String(e.nextElement().getHardwareAddress()));
			}
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
