package com.nothing.url_cess_bg.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.nothing.url_cess_bg.dao.ICessDbHandler;
import com.nothing.url_cess_bg.entity.Url;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@DisallowConcurrentExecution
public class CessUrlStatusJob implements Job {

//	
	public CessUrlStatusJob() {
		super();
	}
//

	static {
		log.info("CessUrlStatusJob created");
	}

//	public CessUrlStatusJob(UrlRepository urlRepository, ExecutorService executorService) {
//		super();
//		this.urlRepository = urlRepository;
//		this.executorService = executorService;
//		
//		log.info("constructor called");
//	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		ApplicationContext springAppContext = JobServiceLayerContext.getApplicationContext();
		JobServiceLayer jobServiceLayer = springAppContext.getBean(JobServiceLayer.class);

		ICessDbHandler urlRepo = jobServiceLayer.getICessDbHandler();
		ExecutorService executorService = null;

		List<Url> urls = urlRepo.findAllUrls();

		// build a thread pool to execute the set the url status;
		ArrayList<SetUrlStatusTask> tasks = new ArrayList<>();

		for (Url url : urls) {

			if (url.getStatus().equalsIgnoreCase("CREATED")) {
				tasks.add(new SetUrlStatusTask(url, urlRepo));
			} else {
				log.error("no task");
			}

		}

		
		log.info("task size {}", tasks.size());
		
		if (tasks.size() != 0) {

			executorService = Executors.newFixedThreadPool(tasks.size());

			log.info("The task size being sent {}", tasks.size());

			List<Future<Url>> results = null;
			try {
				results = executorService.invokeAll(tasks);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			log.info("@@ after setUrlStatus operation ");
			results.forEach(e -> {
				try {
					
				    
					    log.info("{}",e.isDone());
				    	 Url tempUrl = e.get();
							log.info("urlId :: {} --- urlStatus :: {} --- userId :: {} ", tempUrl.getUrlId(),
									tempUrl.getStatus(), tempUrl.getUserId()); 
				     
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				} catch (ExecutionException e1) {
					
			}// catch (TimeoutException e1) {
//					
//					log.error("data not ready");
//				}
			});

		}

		
		if(executorService != null) {
	executorService.shutdown();
	try {
	    if (!executorService.awaitTermination(30, TimeUnit.SECONDS)) {
	        executorService.shutdownNow();
	    } 
	} catch (InterruptedException e) {

		log.error("no tasks available");
	}
	
	}
		// executorService.shutdown()

	}

}
