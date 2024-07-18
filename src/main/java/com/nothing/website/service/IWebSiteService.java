package com.nothing.website.service;

import com.nothing.website.db.schema.Url;

public interface IWebSiteService {
	
	
	String getUserAccessContentStatus(String userId);
	
	Url getUrlStatusByUserId(String userId);

}
