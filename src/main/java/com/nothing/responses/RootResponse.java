package com.nothing.responses;

import java.time.LocalDateTime;

import javax.websocket.server.ServerEndpoint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RootResponse<T> {

	private int code;
	private String message;
	private String status;
	private T response;
	private LocalDateTime timeStamp;

}
