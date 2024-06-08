package com.nothing.odds;

import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HowLongRealTimeScheduler {
	
	
	private static ScheduledExecutorService schedEs = Executors.newScheduledThreadPool(1);
	
	
	
	public static void main(String[] args) {
		
		
		String event = ZonedDateTime.now().toString();
			
		try {
		 	schedEs.scheduleAtFixedRate(() ->{ 	
		 	String response = HowLongRealTimeProcessService.showHowLongResponse(event);
		 	
			System.out.println("The event @ Real-Time : {} ".replace("{}", response));
					 		
		 	}, 0, 1, TimeUnit.MINUTES); // at 0 delay, period of a minute interval

		} catch (Exception e) {

		}
		
		
		
	}

}
