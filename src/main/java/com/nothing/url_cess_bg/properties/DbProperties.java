package com.nothing.url_cess_bg.properties;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter 
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ConfigurationPropertiesScan
public class DbProperties {
	
	

	private String findUrlByUserIdQuery;

	private String findUrlByStatusQuery;
	
	private String findAllUrlsQuery;

	
	private String insertUrlQuery;

	
	private String updateUrlQuery;


}
