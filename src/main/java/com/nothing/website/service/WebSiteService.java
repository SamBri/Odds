package com.nothing.website.service;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nothing.website.dao.UrlRepository;
import com.nothing.website.db.schema.Url;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class WebSiteService implements IWebSiteService {

	
	@Autowired
	UrlRepository urlRepo;
	
	
	
	
	@Override
	public String getUserAccessContentStatus(String userId) {
		
		
		log.info("fetching the url status of the user");
		
		  Url tempUrl =	urlRepo.findUrlStatusByUserId(UUID.fromString(userId));
		  
		  log.info(" {} --  The record -- {}",userId ,tempUrl.toString());
		  
		  String urlStatus;
		  
		  if(tempUrl != null) {
			  
			  urlStatus = tempUrl.getStatus();
		  } else {
			  
			  urlStatus = null;
		  }
		  
		  return urlStatus;
	}




	@Override
	public Url getUrlStatusByUserId(String userId) {
		log.info("fetching the url status of the user");
		
		  Url tempUrl =	urlRepo.findUrlStatusByUserId(UUID.fromString(userId));
		  
		  log.info(" {} --  The record -- {}",userId ,tempUrl.toString());
		  
		  String urlStatus;
		  
		 
		  
		  return tempUrl;
	}
	
	

}
