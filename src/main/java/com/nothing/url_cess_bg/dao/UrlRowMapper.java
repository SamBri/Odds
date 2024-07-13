package com.nothing.url_cess_bg.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;

import com.nothing.url_cess_bg.entity.Url;
import com.nothing.url_cess_bg.utils.UrlCessBgDateTimeFormatter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UrlRowMapper implements RowMapper<Url> {

	
	
	@Override
	public Url mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Url theUrl = new Url();
		
		
		/*SELECT access_duration, created_at, id, updated_at, url_id, user_id, created_by, status, updated_by, url, date_created, expires_at
		FROM public.urls;*/
		
		//2024-07-03 13:09:35.650879+00
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ssx");
		
		
//		String accessDurationDb =rs.getString("access_duration");
//		log.info("the access_duration from the db :: {}",accessDurationDb);
//		Duration accessDuration = Duration.ofSeconds(Long.parseLong(accessDurationDb));
//		log.info("the parsed access_duration {}", accessDuration);
//	     Duration accessDurationDuration = 	rs.getObject("access_duration", Duration.class);
//	     log.info("mapping with jdbc {}", accessDurationDuration);
		
		theUrl.setCreatedAt(UrlCessBgDateTimeFormatter.parseToOffsetDateTime(rs.getString("created_at")));
		theUrl.setCreatedBy(rs.getString("created_by"));
		theUrl.setDuration(rs.getObject("access_duration").toString());
		theUrl.setExpiresAt(UrlCessBgDateTimeFormatter.parseToOffsetDateTime(rs.getString("expires_at")));
		theUrl.setId(Long.parseLong(rs.getString("id")));
		theUrl.setStatus(rs.getString("status"));
		theUrl.setUpdatedAt(UrlCessBgDateTimeFormatter.parseToOffsetDateTime(rs.getString("updated_at")));
		theUrl.setCreatedAt(UrlCessBgDateTimeFormatter.parseToOffsetDateTime(rs.getString("created_at")));
		theUrl.setCreatedBy(rs.getString("created_by"));
		theUrl.setExpiresAt(UrlCessBgDateTimeFormatter.parseToOffsetDateTime(rs.getString("expires_at")));
		theUrl.setId(Long.parseLong(rs.getString("id")));
		theUrl.setStatus(rs.getString("status"));
		theUrl.setUpdatedAt(UrlCessBgDateTimeFormatter.parseToOffsetDateTime(rs.getString("updated_at")));
		theUrl.setUpdatedBy(rs.getString("updated_by"));
		theUrl.setUrl(rs.getString("url"));
		theUrl.setUrlId(UUID.fromString(rs.getString("url_id")));
		theUrl.setUserId(UUID.fromString(rs.getString("user_id")));
		
		return theUrl;
	}
	
	
	public static void main(String[] args) {
		
	//OffsetDateTime dt =	OffsetDateTime.parse("2024-07-03 13:09:35.650879+00");
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ssz");

		
	 String duration = "86400000000000";
	 Duration theDuration = Duration.ofSeconds(Long.parseLong(duration));
	 
	 System.out.println("::"+theDuration);
//	 
//	 Duration createdDuration = Duration.parse("2024-07-09T11:05:22.815058Z");
//	 Duration expiredDuration = Duration.parse("2024-07-09T11:07:22.8140558Z");
	 
	 OffsetDateTime createdOffsetTime = OffsetDateTime.parse("2024-07-09T11:05:22.815058Z");
	 OffsetDateTime expiredOffsetTime = OffsetDateTime.parse("2024-07-09T11:07:22.8140558Z");
	 
	//	Duration theUrlDuration = Duration.between(,expiredOffsetTime);
		
	//	System.out.println("theUrlDuration " + theUrlDuration);

	 
	 
		String psqlDateTime = "2024-07-03 13:09:35.650879+00";
		OffsetDateTime odt;
		String textDate[] = "2024-07-03 13:09:35.650879+00".split("\\+");

		
		for(String text: textDate) {
			System.out.println(text);
		}
		
	    System.out.println(UrlCessBgDateTimeFormatter.parseToOffsetDateTime(psqlDateTime));
		
	}
	
	
	

}
