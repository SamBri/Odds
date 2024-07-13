package com.nothing.cessation.url.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nothing.cessation.url.db.table.Url;


@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
	
	
	Url findUrlStatusByUserId(UUID userId);
	

}
