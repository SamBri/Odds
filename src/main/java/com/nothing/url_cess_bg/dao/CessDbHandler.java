package com.nothing.url_cess_bg.dao;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.nothing.url_cess_bg.entity.Url;
import com.nothing.url_cess_bg.properties.DbProperties;
import com.nothing.url_cess_bg.utils.PostgresStringParser;
import com.nothing.url_cess_bg.utils.UrlCessBgDateTimeFormatter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Getter
@Setter
@ToString
public class CessDbHandler implements ICessDbHandler {

	
	private JdbcTemplate jdbcTemplate;

	
	private DbProperties dbProperties;
	
	
	
	private String findUrlByUserIdQuery;

	private String findUrlByStatusQuery;
	
	private String findAllUrlsQuery;

	
	private String insertUrlQuery;

	
	private String updateUrlQuery;

	
	public CessDbHandler(JdbcTemplate jdbcTemplate, DbProperties dbProperties) {
		
		super();
		
		log.info("The dbProperties shared :: {}", dbProperties);

		this.jdbcTemplate = jdbcTemplate;
		this.dbProperties = dbProperties;
		
		log.info("the copied dbProperties {}", this.dbProperties.toString());
	}
	

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Url> findUrlByUserId(UUID userId) {

		log.info("@@ inside findUrlByUserId");

		log.info("@@ The userId {}", userId);

		
		
		
		
		findUrlByUserIdQuery = dbProperties.getFindUrlByUserIdQuery().replace("?", "\'"+userId.toString()+"\'");

		log.info("@@ The findUrlByUserIdQuery :: {}", findUrlByUserIdQuery);

		List<Url> urls = jdbcTemplate.query(findUrlByUserIdQuery, new UrlRowMapper());

		return urls;
	}

	@Override
	public Url findUrlByStatus(String status) {

		return null;
	}

	@Override
	public List<Url> findAllUrls() {


		log.info("@@ inside findAllUrls");

		findAllUrlsQuery = dbProperties.getFindAllUrlsQuery();
		
		log.info("@@@ sql :: {}", findAllUrlsQuery);
		
		
		
		List<Url> urls = jdbcTemplate.query(findAllUrlsQuery, new UrlRowMapper());

		return urls;
	}

	@Override
	public Boolean save(Url tempUrl) {
		
		
		
		Boolean isDone = false;
		
		
		insertUrlQuery = dbProperties.getInsertUrlQuery();
		updateUrlQuery = dbProperties.getUpdateUrlQuery();
		
		log.info("@@@ inside save");
		if(tempUrl.getId() == null) {
			
			log.info("@@@ inside insert action");
			jdbcTemplate.execute(insertUrlQuery);
			
		}else {
			
			log.info("@@@ inside update action");

			
            // url.updateUrlQuery=UPDATE public.urls SET access_duration=:access_duration, created_at=:created_at, updated_at=:updated_at, url_id=:url_id, user_id=:user_id, created_by=:created_by, status=:status, updated_by=:updated_by, url=:url, date_created=:date_created, expires_at=:expires_at WHERE id=:id;
			updateUrlQuery =	updateUrlQuery
			.replace(":access_duration", PostgresStringParser.wrapString(tempUrl.getDuration().toString()))
			.replace(":created_at", PostgresStringParser.wrapString(tempUrl.getCreatedAt().toString()))
			.replace(":updated_at", PostgresStringParser.wrapString(OffsetDateTime.now().toString()))
			.replace(":url_id", PostgresStringParser.wrapString(tempUrl.getUrlId().toString()))
			.replace(":user_id", PostgresStringParser.wrapString(tempUrl.getUserId().toString()))
			.replace(":created_by", PostgresStringParser.wrapString(tempUrl.getCreatedBy()))
			.replace(":status", PostgresStringParser.wrapString(tempUrl.getStatus()))
			.replace(":updated_by", PostgresStringParser.wrapString("UrlCessBgAgent"))
			.replace(":url", PostgresStringParser.wrapString(tempUrl.getUrl()))
			.replace(":date_created", PostgresStringParser.wrapString(tempUrl.getCreatedAt().toString()))
			.replace(":expires_at",  PostgresStringParser.wrapString(tempUrl.getExpiresAt().toString()))
			.replace(":id", tempUrl.getId().toString());


			log.info("updateUrlQuery {}", updateUrlQuery);

			jdbcTemplate.execute(updateUrlQuery);
			
			isDone = true;

		}
		
		
		return isDone;
	}

	

}
