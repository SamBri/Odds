package com.nothing.responses;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class HowLongExceptionResponse extends RootResponse<String> {

	@JsonProperty("route")
	private String path;
	
	public HowLongExceptionResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HowLongExceptionResponse(int code, String message, String status, String response, LocalDateTime timeStamp) {
		super(code, message, status, response, timeStamp);
	}

	public HowLongExceptionResponse(int code, String message, String status, String response, LocalDateTime timeStamp,
			String path) {
		super(code, message, status, response, timeStamp);
		this.path = path;
	}


	
	
	
	

}
