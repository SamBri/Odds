package com.nothing.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nothing.server.entity.File;


public interface FileScannerServerRepository extends JpaRepository<File,Long> {

	
	
	
	
}