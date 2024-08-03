package com.nothing.apps.find_my_device_ws.exception;


import java.time.OffsetDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nothing.apps.find_my_device_ws.response.RootResponse;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class FindMyDeviceExceptionService {
	
	
	@ExceptionHandler(SaveDeviceException.class)
	public ResponseEntity<RootResponse<String>> handleSaveDeviceExceptionEvent(SaveDeviceException  e){
		
		log.error("@@  handleSaveDeviceExceptionEvent handler");
		
		log.error("SaveDeviceException",e);
		
		RootResponse<String> exceptionResponse = new RootResponse<>();
		
		exceptionResponse.setCode(100);
		exceptionResponse.setMessage(e.getMessage());
		exceptionResponse.setStatus("error");
		exceptionResponse.setResponse(e.getCause().getMessage());
		exceptionResponse.setTimeStamp(OffsetDateTime.now());
		return new ResponseEntity<RootResponse<String>>(exceptionResponse,HttpStatus.EXPECTATION_FAILED);
	}
	
	
	@ExceptionHandler(OwnerDoesNotExistException.class)
	public ResponseEntity<RootResponse<String>> handleOwnerDoesNotExistEvent(SaveDeviceException  e){
		
		log.error("@@  handleOwnerDoesNotExistEvent handler");
		
		log.error("OwnerDoesNotExistException",e);
		
		RootResponse<String> exceptionResponse = new RootResponse<>();
		
		exceptionResponse.setCode(HttpStatus.NOT_FOUND.value());
		exceptionResponse.setMessage(e.getMessage());
		exceptionResponse.setStatus("error");
		exceptionResponse.setResponse(null);
		exceptionResponse.setTimeStamp(OffsetDateTime.now());
		return new ResponseEntity<RootResponse<String>>(exceptionResponse,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(OwnerAlreadyExistException.class)
	public ResponseEntity<RootResponse<String>> handleOwnerAlreadyExistEvent(OwnerAlreadyExistException  e){
		
		log.error("@@  handleOwnerAlreadyExistEvent handler");
		
		log.error("OwnerAlreadyExistException",e);
		
		RootResponse<String> exceptionResponse = new RootResponse<>();
		
		exceptionResponse.setCode(HttpStatus.CONFLICT.value());
		exceptionResponse.setMessage(e.getMessage());
		exceptionResponse.setStatus("error");
		exceptionResponse.setResponse(null);
		exceptionResponse.setTimeStamp(OffsetDateTime.now());
		return new ResponseEntity<RootResponse<String>>(exceptionResponse,HttpStatus.CONFLICT);
	}
	
	
	@ExceptionHandler(InvalidEmailAddressException.class)
	public ResponseEntity<RootResponse<String>> handleInvalidEmailAddressException(InvalidEmailAddressException  e){
		
		log.error("@@  handleInvalidEmailAddressException handler");
		
		log.error("InvalidEmailAddressException",e);
		
		RootResponse<String> exceptionResponse = new RootResponse<>();
		
		exceptionResponse.setCode(102);
		exceptionResponse.setMessage(e.getMessage());
		exceptionResponse.setStatus("error");
		exceptionResponse.setResponse(null);
		exceptionResponse.setTimeStamp(OffsetDateTime.now());
		return new ResponseEntity<RootResponse<String>>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(InvalidMobileNumberException.class)
	public ResponseEntity<RootResponse<String>> handleMobileNumberExceptionEvent(InvalidMobileNumberException  e){
		
		log.error("@@  handleMobileNumberExceptionEvent handler");
		
		log.error("InvalidMobileNumberException",e);
		
		RootResponse<String> exceptionResponse = new RootResponse<>();
		
		exceptionResponse.setCode(103);
		exceptionResponse.setMessage(e.getMessage());
		exceptionResponse.setStatus("error");
		exceptionResponse.setResponse(null);
		exceptionResponse.setTimeStamp(OffsetDateTime.now());
		return new ResponseEntity<RootResponse<String>>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
	
	

}
