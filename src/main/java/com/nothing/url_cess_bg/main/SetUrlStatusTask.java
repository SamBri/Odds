package com.nothing.url_cess_bg.main;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.Callable;

import com.nothing.url_cess_bg.dao.ICessDbHandler;
import com.nothing.url_cess_bg.entity.Url;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class SetUrlStatusTask implements Callable<Url> {

	private Url url;

	private ICessDbHandler urlRepo;

	@Override
	public  Url call() throws Exception {
		log.info("@@ going to set the url status to expired");

		Url tempUrl = url;

		
		log.info("urlId :: {} --- urlStatus :: {} --- userId :: {} ", tempUrl.getStatus(),
				tempUrl.getUserId());
		List<Url> urls;
		urls = urlRepo.findUrlByUserId(url.getUserId());

		tempUrl = urls.iterator().next();

		Duration accessDuration = Duration.parse(tempUrl.getDuration()).abs();
		Duration theUrlDuration = Duration.between(OffsetDateTime.now(), tempUrl.getExpiresAt());

		log.info("accessDuration {} :: userId {} ", accessDuration.toSeconds(), tempUrl.getUserId());
		log.info("theUrlDuration {} :: userId {} ", theUrlDuration.toSeconds(), tempUrl.getUserId());

		if (theUrlDuration.isNegative()) {
			tempUrl = urls.iterator().next();

			log.info("The url has expired");

			String urlStatus = null;

			if (tempUrl != null) {


				tempUrl.setStatus("EXPIRED");

				log.info("the object going to be updated {}", tempUrl.toString());

				Boolean status = urlRepo.save(tempUrl);

				log.info("save action :: urlId - {} :: userId - {} :: isDone {} :: ", tempUrl.getUrl(),tempUrl.getUserId(), status);

			} else {

				
			}

		} else {
			
			log.info("The url has not expired");

			log.info("urlId :: {} --- urlStatus :: {} --- userId :: {} ", tempUrl.getUrlId(), tempUrl.getStatus(),
					tempUrl.getUserId());

		}

		return tempUrl;
	}

}
