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

		try {

			// 1. call the how long service
			String event = ZonedDateTime.now().toString();
			// 2. send signal to the service for duration
			String howLong = showHowLongResponse(event);

			System.out.println(howLong);
			// 3. show the real time duration at every clock tick

		} catch (Exception e) {

			System.err.println(e);

		}
	}

}
