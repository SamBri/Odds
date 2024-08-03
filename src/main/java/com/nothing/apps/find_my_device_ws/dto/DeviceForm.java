package com.nothing.apps.find_my_device_ws.dto;

import java.util.UUID;

import com.nothing.apps.find_my_device_ws.db.Device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DeviceForm extends Device {

	@Override
	public String getDeviceModel() {
		return this.deviceModel;
	}

	@Override
	public String getDeviceType() {
		return this.deviceType;
	}

	@Override
	public String getHardwareAddress() {
		return this.hardwareAddress;
	}

	@Override
	public String getSerialNumber() {
		return this.serialNumber;
	}

	@Override
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	@Override
	public void setDeviceType(String deviceType) {
		this.setDeviceType(deviceType);
	}

	@Override
	public void setHardwareAddress(String hardwareAddress) {
		this.setHardwareAddress(hardwareAddress);
	}

	@Override
	public void setOwnerId(String ownerId) {
		this.setOwnerId(ownerId);
	}

	@Override
	public void setSerialNumber(String serialNumber) {
		this.setSerialNumber(serialNumber);
	}

	public static void main(String[] args) {
		DeviceForm form = new DeviceForm();
	}

}
