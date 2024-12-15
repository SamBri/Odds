package com.nothing.server;

import java.util.Arrays;
import java.util.EnumSet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

import com.nothing.server.filters.SSEResponseConversionFilter;

import jakarta.servlet.DispatcherType;

@SpringBootApplication
public class FileScannerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileScannerServerApplication.class, args);
	}

	@Bean
	FilterRegistrationBean<SSEResponseConversionFilter> filterRegistrationBean() {
		FilterRegistrationBean<SSEResponseConversionFilter> registration = new FilterRegistrationBean<>(
				new SSEResponseConversionFilter());
		registration.setAsyncSupported(true);
		registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
		registration.setOrder(0);
		registration.setUrlPatterns(Arrays.asList("/api/file/scan"));
		return registration;
	}
	
	
//	@Bean
//	FilterRegistrationBean<SSEResponseConversionFilter2> filterRegistrationBeanFilter() {
//		FilterRegistrationBean<SSEResponseConversionFilter2> registration = new FilterRegistrationBean<>(
//				new SSEResponseConversionFilter2());
//		registration.setAsyncSupported(true);
//		registration.setOrder(1);
//		registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
//		registration.setUrlPatterns(Arrays.asList("/api/file/scan"));
//		return registration;
//	}
//	
	

}
