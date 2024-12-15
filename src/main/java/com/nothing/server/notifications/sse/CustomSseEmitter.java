package com.nothing.server.notifications.sse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class CustomSseEmitter extends SseEmitter {

	@Override
	protected void extendResponse(ServerHttpResponse outputMessage) {
		HttpHeaders headers = outputMessage.getHeaders();
		if (headers.getContentType() == null) {
			headers.setContentType(MediaType.TEXT_PLAIN);
		}
	}

	public CustomSseEmitter(Long timeout) {
		super(timeout);
		// TODO Auto-generated constructor stub
	}
	
	
	

}
