package com.nothing.apps.find_my_device_ws.utils;


import java.util.regex.PatternSyntaxException;

import com.nothing.apps.find_my_device_ws.exception.InvalidMobileNumberException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringUtil {

	public static boolean isEmail(String arg) throws InvalidEmailAddressException   {

		log.info("inside isEmail method");

		boolean test = false;

	
			test = arg.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") ? true : false;
			
			if(test == false) {
				throw new InvalidEmailAddressException("invalid emailAddress : {}".replace("{}", arg));
			}
		
		return test;

	}

	public static boolean isMobileNumber(String arg) throws InvalidMobileNumberException  {

		log.info("inside isMobileNumber method");
		
		boolean test = false;

		try {
			test = arg.matches("^\\d{10}$") ? true : false;
			
			if(test == false) {
				throw new InvalidMobileNumberException("invalid mobileNumber : {}".replace("{}", arg));
			}

		} catch (PatternSyntaxException e) {
			log.error("PatternSyntaxException :: ", e);
		}

		return test;

	}
	
	
	public static void main(String[] args) {
		
		try {
			
			
			System.out.println("848-859-7731".matches("^\\d{10}$"));
			System.out.println(isMobileNumber("848-859-7731"));
		} catch (InvalidMobileNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
