package com.nothing.url_cess_bg.main;


import static org.quartz.SimpleScheduleBuilder.*;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.JobBuilder.newJob;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UrlCessScheduler {

	
	private ApplicationContext appContext;


	public static void start(ApplicationContext appContext) {

		try {

			JobServiceLayerContext.setApplicationContext(appContext);
			
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			

			log.info("@@ inside  UrlCessScheduler main");

			scheduler.start();

			log.info("@@ UrlCessScheduler started");

			JobDetail job = newJob(CessUrlStatusJob.class)
					.withIdentity("cess-url-status-job", 
							"group1").build();

			Trigger trigger = newTrigger().withIdentity("cess-url-status-job-trigger")
					.withSchedule(cronSchedule("* * * * * ?"))
					.forJob("cess-url-status-job", "group1").build();

			
			scheduler.scheduleJob(job, trigger);
			
		


		
		} catch (Exception e) {

		}

	}
	
	
	
	
	
	
}

	
	

