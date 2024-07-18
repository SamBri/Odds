package com.nothing.website.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nothing.website.db.schema.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
	
	
	Url findUrlStatusByUserId(UUID userId);
	
	

}
