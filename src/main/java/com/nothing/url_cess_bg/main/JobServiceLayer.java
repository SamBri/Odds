package com.nothing.url_cess_bg.main;

import java.util.concurrent.ExecutorService;

import com.nothing.url_cess_bg.dao.ICessDbHandler;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JobServiceLayer {

	public JobServiceLayer() {

	}


	private ICessDbHandler iCessDbHandler;
	
	private ExecutorService executorService;

	public JobServiceLayer(ICessDbHandler iCessDbHandler, ExecutorService executorService) {
		super();
		this.iCessDbHandler = iCessDbHandler;
		this.executorService = executorService;
	}

	public JobServiceLayer(ICessDbHandler iCessDbHandler) {
		super();
		this.iCessDbHandler = iCessDbHandler;
	}


	
	

	
	






}
