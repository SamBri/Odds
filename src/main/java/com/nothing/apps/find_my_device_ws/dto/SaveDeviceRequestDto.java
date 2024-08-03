package com.nothing.apps.find_my_device_ws.dto;

import java.util.List;
import java.util.Set;

import com.nothing.apps.find_my_device_ws.db.Device;

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

	
	List<Set<DeviceForm>> deviceList;

	private String ownerId;

}
