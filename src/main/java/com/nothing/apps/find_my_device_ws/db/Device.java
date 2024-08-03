package com.nothing.apps.find_my_device_ws.db;

import java.util.UUID;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "device")
public class Device {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "device_id", nullable = false)
	private UUID deviceId;

	@Column(name = "serial_number", unique = true, nullable = false)
	protected String serialNumber;

	@Column(name = "device_type", nullable = false)
	protected String deviceType;

	@Column(name = "device_model", nullable = false)
	protected String deviceModel;

	@Column(name = "owner_id", nullable = false)
	protected String ownerId;

	@Column(name = "hardware_address", unique = true, nullable = false)
	protected String hardwareAddress;

}
