package com.nothing.url_cess_bg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.nothing.url_cess_bg.main.JobServiceLayerContext;
import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nothing.url_cess_bg.main.UrlCessScheduler;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class UrlCessBgApplication {

	public static void main(String[] args) {

		ApplicationContext appContext = SpringApplication.run(UrlCessBgApplication.class, args);
		UrlCessScheduler.start(appContext);
	}
}
