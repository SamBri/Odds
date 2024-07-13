package com.nothing.url_cess_bg.utils;



import java.security.Signature;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import java.time.ZonedDateTime;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UrlCessBgDateTimeFormatter {

	public static LocalDateTime parseToLocalDateTime(String psqlDateTime) {

		String dateTimeArgs[] = null;

		String date;
		String time;
		String offset;

		dateTimeArgs = psqlDateTime.split(" ");

		date = dateTimeArgs[0];
		time = dateTimeArgs[1];
		
		log.info("date {}",date);
		log.info("time {}",time);

		if (time.contains("+")) {

			log.info("time construct {}","+");

			dateTimeArgs = time.split("\\+");
			
			for(String t : dateTimeArgs) {
				log.info("time split {}",t);
			}
			
			offset = dateTimeArgs[1];
			time = dateTimeArgs[0];

			log.info("final time {}",time);

		}

		if (time.contains("-")) {
			log.info("time construct {}","-");

			dateTimeArgs = time.split("\\+");
			
			
			for(String t : dateTimeArgs) {
				log.info("time split {}",t);
			}
			offset = dateTimeArgs[1];
			time = dateTimeArgs[0];
			
			log.info("final time {}",time);

		}
		
		return LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));

	}

	
	public static OffsetDateTime parseToOffsetDateTime(String psqlDateTime) {

		String dateTimeArgs[] = null;

		String date;
		String time;
		String offset = null;

		dateTimeArgs = psqlDateTime.split(" ");

		date = dateTimeArgs[0];
		time = dateTimeArgs[1];
		
		log.info("date {}",date);
		log.info("time {}",time);
		String sign = null;

		if (time.contains("+")) {

			log.info("time construct {}","+");

			dateTimeArgs = time.split("\\+");
			
			for(String t : dateTimeArgs) {
				log.info("time split {}",t);
			}
			
			offset = dateTimeArgs[1];
			time = dateTimeArgs[0];
			sign = "+";

			log.info("final time {}",time);

		}

		if (time.contains("-")) {
			log.info("time construct {}","-");

			dateTimeArgs = time.split("\\+");
			
			
			for(String t : dateTimeArgs) {
				log.info("time split {}",t);
			}
			offset = dateTimeArgs[1];
			sign = "-";
			time = dateTimeArgs[0];
			
			log.info("final time {}",time);

		}
		
		
		return OffsetDateTime.of(LocalDate.parse(date), LocalTime.parse(time),ZoneOffset.of(sign.concat(offset)));

	}

	
	
	

}


