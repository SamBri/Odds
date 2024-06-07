package com.nothing.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.nothing.IllegalDeploymentException;
import com.nothing.responses.HowLongExceptionResponse;
import com.nothing.responses.RootResponse;

@ControllerAdvice
public class ApplicationExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<RootResponse<String>> illegalDeploymentExceptionHandler(IllegalDeploymentException e){
		String message = e.getMessage();
		RootResponse<String> exceptionResponse =  new RootResponse<>(HttpStatus.PRECONDITION_FAILED.value(),message,"error","failed",LocalDateTime.now());
		return new ResponseEntity<RootResponse<String>>(exceptionResponse ,HttpStatus.PRECONDITION_FAILED);

	}
	
	
	@ExceptionHandler
	public ResponseEntity<HowLongExceptionResponse> howLongExceptionHandler(HowLongException e,  HttpServletRequest request){
		String message = e.getMessage();
		String path =request.getServletPath().toString();
		Throwable cause = e.getCause();
		message = cause.getMessage();
		HowLongExceptionResponse exceptionResponse =  new HowLongExceptionResponse(100,message,"error","failed",LocalDateTime.now(),path);
		return new ResponseEntity<HowLongExceptionResponse>(exceptionResponse ,HttpStatus.EXPECTATION_FAILED);

	}

}
