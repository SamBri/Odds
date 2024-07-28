package com.nothing.apps.find_my_device_ws.dto;


import com.nothing.apps.find_my_device_ws.db.Owner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveDeviceRequestDto {
	
	private String ownerEmailAddress;
	
	private String ownerMobileNumber;

	private String serialNumber;
	
	private String deviceType;
	
	private String deviceModel;
	
	private String hardwareAddress;

	

}
