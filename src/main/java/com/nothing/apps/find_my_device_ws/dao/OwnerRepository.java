package com.nothing.apps.find_my_device_ws.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nothing.apps.find_my_device_ws.db.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
	
	Owner findOwnerByEmailAddress(String emailAddress);

	Owner findOwnerByMobileNumber(String mobileNumber);

	Owner findOwnerByOwnerId(String OwnerId);

}
