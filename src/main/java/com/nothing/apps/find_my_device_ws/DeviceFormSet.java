package com.nothing.apps.find_my_device_ws;

import java.util.HashSet;

import com.nothing.apps.find_my_device_ws.dto.DeviceForm;
import com.nothing.apps.find_my_device_ws.exception.DuplicateHardwareAddressException;

/**
 * avoid duplicate entries
 * 
 * @author codefilms
 *
 */
public class DeviceFormSet extends HashSet<DeviceForm> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1555430606370345848L;

	// check if devices being added are the same.
	@Override
	public boolean add(DeviceForm deviceForm) {
	 return	customAdd(deviceForm);
	}
	
	
	public boolean customAdd(DeviceForm deviceForm) throws DuplicateHardwareAddressException {

		System.err.println("adding devices :: " + deviceForm);

		// check for unique form entries
		if (this.contains(deviceForm)) {
			System.err.println("duplicate form was detected");
			System.err.println("taking only one instance");
			return false;
		} else {
			System.err.println("all entries are unique!");

			// before you add to list, no two devices can have the same hardware address.

			if (this.duplicateHardwareAddress(deviceForm)) {

				throw new DuplicateHardwareAddressException("duplicate hardware address detected for devices sent");

			} else {
				super.add(deviceForm); // mother list object

			}

		}

		return true;
	}

	// does the list of devices have an entry request
	public Boolean duplicateHardwareAddress(DeviceForm entry) {

		for (DeviceForm form : this) {

			System.err.println("The list of devices : " + this.size());

			if (form.getHardwareAddress().equals(entry.getHardwareAddress())) {

				return true;
			}

		}

		return false;
	}

	@Override
	public boolean contains(Object o) {

		for (DeviceForm form : this) {

			System.err.println("The list of devices : " + this.size());

			if (form.equals(o)) {

				return true;
			}

		}

		return false;
	}

}
