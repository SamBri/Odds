package com.nothing.cessation.url.db.table;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="urls")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Url {
	
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	@Id
	private Long id;
	
	@Column(name="url_id")
	private UUID urlId;
	
	@Column(name="user_id")
	private UUID userId;
	
	@Column(name="url")
	private String url;
	
	@JsonProperty("access_duration")
	@Column(name="access_duration")
	private String duration;
	
	@Column(name="status")
	private String status;
	

//	@CreationTimestamp
//	@Column(name="date_created")
//	private ZonedDateTime dateCreated;
	
	@Column(name="expires_at")
	private ZonedDateTime expiresAt;
	
	
	@CreationTimestamp
	@Column(name="created_at")
	private ZonedDateTime createdAt;
	
	@Column(name="created_by")
	private String createdBy;
	
	
	@Column(name="updated_by")
	private String updatedBy;
	
	
	@UpdateTimestamp
	@Column(name="updated_at")
	private ZonedDateTime updatedAt;
	

}
