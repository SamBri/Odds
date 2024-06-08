package com.nothing.odds;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

public class HowLongRealTimeProcessService {

	private static RestTemplate restTemplate;

	static {

		System.out.println("static block");
		restTemplate = new RestTemplate();
		ResourceBundle rb = ResourceBundle.getBundle("how-long-service");
		howLongServiceUrl = rb.getString("how-long-url-service");
	}

	private static String howLongServiceUrl;

	public static String showHowLongResponse(String event) {

		howLongServiceUrl = howLongServiceUrl.replace("{}", event);

		ResponseEntity<HowLongResponse> response = null;
		try {

			System.out.println("the url @@ {} " + howLongServiceUrl);

			response = restTemplate.getForEntity(howLongServiceUrl, HowLongResponse.class);

			if (!response.getStatusCode().is2xxSuccessful()) {

				return null;
			}
		} catch (Exception e) {

			System.out.println(e);

		}

		return response.getBody().getHowLong();

	}

	public static void main(String[] args) {

		String howLong = null;

		String theEvent = ZonedDateTime.now().toString();
		
		System.out.println("The Event TimeStamp :: " + theEvent);

		while (true) {

			try {

				// 1. call the how long service
				
				System.out.println("The Event TimeStamp :: " + theEvent);


				howLong = showHowLongResponse(theEvent);

				System.out.println("The event @ Real-Time : {} ".replace("{}", howLong));
				
				
				Thread.sleep(60000); 

				/*
				 * As the processor is running at clock speed. 1. store the created event. 2.
				 * tick the call to the api at every instant 3. have the new howLongResponse
				 * communicated
				 */

				// 2. send signal to the service for duration

				// 3. show the real time duration at every clock tick

			} catch (Exception e) {

				System.err.println(e);

			}

		}
		
		
		

	}

}
