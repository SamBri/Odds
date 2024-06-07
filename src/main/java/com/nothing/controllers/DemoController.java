package com.nothing.controllers;


import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nothing.IllegalDeploymentException;
import com.nothing.responses.RootResponse;


@RestController
@RequestMapping("/api")
public class DemoController {
	
	
	// define our simple endpoint
	@GetMapping("/greetings")
	public ResponseEntity<RootResponse<String>> sayHello() {
				
		
		String message;
		RootResponse<String> rootResponse;
		// we will say a different message if the deployment was successfully on that vm.
		if(System.getProperty("os.name").contains("Linux"))
		{
			 message ="application was successfully deployed on the vm.";
			 rootResponse =	 new RootResponse<>(200,message,"success","Hello, Welcome Here!",LocalDateTime.now());
		}
		
		else {
			
			message = "deployment was not done on the expected vm.";
			throw new IllegalDeploymentException(message);
		}
		
		
		return new ResponseEntity<RootResponse<String>>(rootResponse ,HttpStatus.OK);
	}
	
	
	
	

}
