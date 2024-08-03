package com.nothing.apps.find_my_device_ws.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDetailsFormDto {
	
	private String firstName;

	private String lastName;

	private String emailAddress;

	private String mobileNumber;

	private String country;

	private String countryCode;

}
