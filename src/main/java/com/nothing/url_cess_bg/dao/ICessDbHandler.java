package com.nothing.url_cess_bg.dao;

import java.util.List;
import java.util.UUID;

import com.nothing.url_cess_bg.entity.Url;

public interface ICessDbHandler {

	List<Url> findUrlByUserId(UUID userId);
	
	Url findUrlByStatus(String status);
	
	
	List<Url> findAllUrls();

	Boolean save(Url tempUrl);
}
