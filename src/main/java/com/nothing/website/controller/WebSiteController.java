package com.nothing.website.controller;

import java.io.IOException;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.nothing.website.db.schema.Url;
import com.nothing.website.service.IWebSiteService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebSiteController {
	
	
	
	@Autowired
	IWebSiteService webSiteService;
	
	
	@GetMapping("/ping")
	@ResponseBody
	public String doPing() {
		return "ping";
	}
	
	
	
	@GetMapping("/user-access/contents/{userId}")
	public String showUserContentPage(@PathVariable String userId, Model theModel) {
		
		
		log.info("@@ inside showUserContentPage");
		
		String userPage = null;

		
		
          Url tempUrl = webSiteService.getUrlStatusByUserId(userId);
		
		if(tempUrl.getStatus().equalsIgnoreCase("EXPIRED")) {
			
			
			userPage = "expired-page";
	
			
		} else {
			
			
		  userPage = "user-content-page";
	      theModel.addAttribute("expiresAt", tempUrl.getExpiresAt());
		}
		

		
		return userPage;
	}
	

	
	

}
