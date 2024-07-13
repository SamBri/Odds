package com.nothing.cessation.url.service;

import com.nothing.cessation.url.db.table.Url;
import com.nothing.cessation.url.dto.CreateUrlCessationDto;
import com.nothing.cessation.url.exception.SaveUrlException;

public interface IUrlCessationService {
	
	Url createUrl(CreateUrlCessationDto dto) throws SaveUrlException;

    String getUrlStatusByUserId(String userId);

}
