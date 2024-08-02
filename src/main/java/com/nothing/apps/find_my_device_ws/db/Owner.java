package com.nothing.apps.find_my_device_ws.db;

import java.util.UUID;

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

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owner")
public class Owner {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email_address", nullable = false, unique = true)
	private String emailAddress;

	@Column(name = "mobile_number", nullable = false, unique = true)
	private String mobileNumber;

	@Column(name = "country")
	private String country;

	@Column(name = "country_code")
	private String countryCode;

	@Column(name = "owner_id")
	private String ownerId; // string, some avator characters

	@Column(name = "owner_no",unique = true)
	private Long ownerNo; // integers, random
	

	@Column(name = "owner_ref",unique = true)
	private UUID ownerRefNo; // a uuid for as a mirror

}
