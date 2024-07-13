package com.nothing.cessation.url.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nothing.cessation.url.dao.UrlRepository;
import com.nothing.cessation.url.db.table.Url;
import com.nothing.cessation.url.dto.CreateUrlCessationDto;
import com.nothing.cessation.url.exception.SaveUrlException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UrlCessationService implements IUrlCessationService {
	
	UrlRepository urlRepo;

	public UrlCessationService(UrlRepository urlRepo) {
		super();
		this.urlRepo = urlRepo;
	}
	
	@Value("${url.prefix}")
	private String urlPrefix;
	
	
	public Url createUrl(CreateUrlCessationDto dto) throws SaveUrlException {
		
		
		UUID theUserId = dto.getUserId();
		log.info("creating the cess url for user {}", theUserId);
		
		String userCessUrl = urlPrefix.replace("{userId}", theUserId.toString());
		
		
//		log.info("the cess url for user {}", userCessUrl);
//		
//		log.info("encoding the url");
//		
//		try {
//			userCessUrl =	URLEncoder.encode(userCessUrl, StandardCharsets.ISO_8859_1.toString());
//		
//			log.info("the encoded url the {}",userCessUrl);
//
//		
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		

		
		
		
		Url tempUrl = new Url();
		tempUrl.setDuration(dto.getPeriod().toString());
		tempUrl.setUrl(userCessUrl);
		tempUrl.setUrlId(UUID.randomUUID());
		tempUrl.setStatus("CREATED");
		tempUrl.setUserId(dto.getUserId());
		tempUrl.setCreatedBy(dto.getUserId().toString());
		tempUrl.setExpiresAt(ZonedDateTime.now().plusSeconds(dto.getPeriod().getSeconds()));
		
	    tempUrl =	urlRepo.save(tempUrl);
	    
	    log.info("@@@ tempUrl from Hiberante - JPA {}",tempUrl);
	    
	    if(tempUrl != null) {
	    	
	    	log.info("@@ url created");
	    	
	    } else {
	    	
	    	log.error("url creation failed.");
	    	
	    	throw new SaveUrlException("Url could not be created");
	    }
	    
	    return tempUrl;
		
		
		
	}


	
	
	
	
	
	@Override
	public String getUrlStatusByUserId(String userId) {
		
		
		log.info("fetching the url status of the user");
		
	  Url tempUrl =	urlRepo.findUrlStatusByUserId(UUID.fromString(userId));
	  
	  
	  String urlStatus;
	  
	  if(tempUrl != null) {
		  
		  urlStatus = tempUrl.getStatus();
	  } else {
		  
		  urlStatus = null;
	  }
	  
	  return urlStatus;
		
		
	}
	

}
