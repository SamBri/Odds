package com.nothing.apps.find_my_device_ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.nothing.unified_authentication_service_resource_server.UnifiedAuthenticationServiceResourceServerApplication;





@SpringBootApplication
@Import({UnifiedAuthenticationServiceResourceServerApplication.class})
public class FindMyDeviceWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindMyDeviceWsApplication.class, args);
	}

}
