package com.nothing.url_cess_bg.main;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.nothing.url_cess_bg.dao.CessDbHandler;
import com.nothing.url_cess_bg.dao.ICessDbHandler;
import com.nothing.url_cess_bg.properties.DbProperties;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DbConfig {
	
//	Time - space - closed
//	@Value("${url.get.userId}")
//	private String findUrlByUserIdQuery;
//
//	@Value("${url.get.status.userId}")
//	private String findUrlByStatusQuery;
//	
//	@Value("${url.get.all}")
//	private String findAllUrlsQuery;

//	@Bean
//	ExecutorService executorService() {
//
//		log.info("@@ executorService created  ");
//		return Executors.defaultThre
//	}

	@Bean
	public DataSource myPostgresSQLDateSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/url-cessation-service-db");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgresIsBae@CSS#24");
		

		return dataSource;
	}

	@Bean
	JdbcTemplate myJdbcTemplate() {
		
		return new JdbcTemplate(myPostgresSQLDateSource());
	}
	
	
	
	@Bean
    @ConfigurationProperties(prefix = "url")
	DbProperties dbProperties() {
		
		return new DbProperties();
		
	}

	@Bean
	CessDbHandler postgresCessDbHander() {
		
		CessDbHandler cessDbHandler = new CessDbHandler(myJdbcTemplate(),dbProperties());
		
		log.info("dbProperties :: {}", dbProperties().toString());
		
		return cessDbHandler;
	}
	

	

	@Bean
	JobServiceLayer jobServiceLayer() {
		log.info("@@@ jobServiceLayer created");
		
		log.info("@@@ jobServiceLayer see dbProperties {}",postgresCessDbHander().getDbProperties().toString());

		
		JobServiceLayer jbs =new JobServiceLayer(postgresCessDbHander());
		
		return jbs;
	}

}
