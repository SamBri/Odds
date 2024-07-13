package com.nothing.cessation.url;

import java.time.ZonedDateTime;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nothing.cessation.url.db.table.Url;
import com.nothing.cessation.url.dto.CreateUrlCessationDto;
import com.nothing.cessation.url.exception.SaveUrlException;
import com.nothing.cessation.url.response.RootResponse;
import com.nothing.cessation.url.service.IUrlCessationService;


@RestController
public class UrlCessationController {

	IUrlCessationService urlCessationService;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(UrlCessationController.class);


	public UrlCessationController(IUrlCessationService urlCessationService) {
		this.urlCessationService = urlCessationService;
	}

	// Create a Cessation Url.
	@RequestMapping(value="/api/cessations/urls",method= RequestMethod.POST)
	public ResponseEntity<RootResponse<Url>> createUrlCessation(@RequestBody CreateUrlCessationDto urlDto) throws SaveUrlException {

		LOGGER.info("@@ inside createUrlCessation");
		
		
		RootResponse<Url> rootResponse = new RootResponse<>();

		LOGGER.info("The createUrlCessation request : {}", new JSONObject(urlDto));
		
		Url theCreatedUrl =	urlCessationService.createUrl(urlDto);
		
		if(theCreatedUrl != null) {
			rootResponse.setCode(HttpStatus.CREATED.value());
			rootResponse.setMessage("url has been successfully created!");
			rootResponse.setResponse(theCreatedUrl);
			rootResponse.setStatus(HttpStatus.CREATED.name().toUpperCase());
			rootResponse.setTimestamp(ZonedDateTime.now());
		
		}
			

		
		
		return new ResponseEntity<RootResponse<Url>>(rootResponse,HttpStatus.CREATED);

	}

	// Cessation Url webhook.
	// Create a Cessation Url.
	@GetMapping("/user-access/contents/{userId}")
	public String getUserContents(@PathVariable  String userId) {

		LOGGER.info("@@ inside getUserContents");

		String userUrlStatus = urlCessationService.getUrlStatusByUserId(userId);
		
		if(userUrlStatus.equalsIgnoreCase("EXPIRED")) {
			
			return null;
		} else {
			
			
			// user accesses the content of the user.
			
			// could be a redirect:
			
		}
		
		
		String theUserId = userId;
		
		return theUserId;

	}


}
