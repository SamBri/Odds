package com.nothing.url_cess_bg.entity;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;





@Getter
@Setter
@ToString
@NoArgsConstructor
public class Url {
	
	
	
	private Long id;
	
	private UUID urlId;
	
	private UUID userId;
	
	private String url;
	
	
	private String duration;

	private String status;
	
	
	private OffsetDateTime expiresAt;
	
	
	private OffsetDateTime createdAt;
	
	private String createdBy;
	
	
	private String updatedBy;
	
	

	private OffsetDateTime updatedAt;
	

}
