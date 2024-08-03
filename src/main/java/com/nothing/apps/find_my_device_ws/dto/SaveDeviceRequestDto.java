package com.nothing.apps.find_my_device_ws.dto;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;

import com.nothing.apps.find_my_device_ws.DeviceFormSet;

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

	DeviceFormSet deviceList;

	private String ownerId;

	public static void main(String[] args) {
		Set<String> uniqueNames = new HashSet<>();

		DeviceFormSet uniqueForms = new DeviceFormSet();
		
		try {

			DeviceForm form1 = new DeviceForm();
			form1.setDeviceId(null);
			form1.setDeviceModel("SM-21");
			form1.setDeviceType("MOBILE");
			form1.setHardwareAddress("c1:7c:0c:87:5c:19");
			form1.setOwnerId("Aaron.Hane");
			form1.setSerialNumber("SN6743");

			DeviceForm form2 = new DeviceForm();
			form2.setDeviceId(null);
			form2.setDeviceModel("SM-22");
			form2.setDeviceType("MOBILE");
			form2.setHardwareAddress("c1:7c:0c:87:5c:19");
			form2.setOwnerId("Aaron.Hane");
			form2.setSerialNumber("SN6743");

			System.out.println("hashCode :: form1" + form1.hashCode());
			System.out.println("hashCode :: form2" + form2.hashCode());
			System.out.println("form1 string data::" + form1.toString());
			System.out.println("form2 string data::" + form2.toString());

			System.out.println("form1 == form2" + form1.equals(form2));

			System.out.println(uniqueForms.add(form1));
			System.out.println(uniqueForms.add(form2));
			
		
			System.out.println(new JSONObject(form1));
			System.out.println(new JSONObject(form2));

			
			
			
			

			System.out.println(uniqueForms);

		} catch (Exception e) {

			System.err.println("inside");
			System.err.println(e);

		}

//		uniqueNames.add("peter");
//		uniqueNames.add("peter");
//		uniqueNames.add("james");
//		uniqueNames.add("mary");
//		uniqueNames.add("francis");

//		System.out.println(uniqueNames);
	}

}
