package com.nothing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HowLongWebController {
	
	@GetMapping(value ="/showHowLong")
	public String showHowLong() {

		return "display-how-long";
	}
	
	

}
