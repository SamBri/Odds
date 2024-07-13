package com.nothing.url_cess_bg.main;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component
public class JobServiceLayerContext {
	

	private static ApplicationContext applicationContext;
	
	
	
	
	public static void setApplicationContext(ApplicationContext applicationContext)  {
	
		JobServiceLayerContext.applicationContext = applicationContext;
	}



	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	
	

}
