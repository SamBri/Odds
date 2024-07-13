package com.nothing.url_cess_bg.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostgresStringParser {

	public static String wrapString(String text) {

		log.info("@@@ inside wrapString");

		log.info("@@@ raw string {}", text);

		text = "'".concat(text).concat("'");

		log.info("@@@ wrapped string {}", text);

		return text;
	}

}
