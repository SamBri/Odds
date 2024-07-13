package com.nothing.cessation.url.dto;

import java.time.Duration;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateUrlCessationDto {
	
	@JsonIgnore
	private String webhook;
	
	@JsonProperty("access_duration")
	private Duration period	;
	
	private UUID userId;
	
	

}
