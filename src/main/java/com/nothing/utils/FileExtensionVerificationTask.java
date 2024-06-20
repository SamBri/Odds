package com.nothing.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class FileExtensionVerificationTask implements Runnable {

	private ResponseBodyEmitter emitter;

	private List<MultipartFile> list;

	@Value("#{'${LIST_OF_ILLEGAL_FILES_EXT}'.split(',')}")
	private List<String> illegalFileExtlist;

	private ExecutorService exeService;

	@Override
	public void run() {

		List<MultipartFile> files = list;

		for (MultipartFile file : list) {

			String theFile = file.getOriginalFilename();

			List<Future<FileScanResult>> values = scanFiles(theFile);
			
			
			values.forEach(System.out::println);

			for (Future<FileScanResult> future : values) {
				try {

					FileScanResult fileScanResult = future.get(); 
					
					if (!fileScanResult.getResult().equalsIgnoreCase("ACCEPTABLE")) {

						emitter.send(fileScanResult.getMessage());

					} else {
						
						System.out.println("This file extension, {} ,is acceptable".replace("{}",fileScanResult.getFileExt()));
					//	emitter.send(fileScanResult.getMessage());

					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		
		}
		
		emitter.complete();


		

	}

	private List<Future<FileScanResult>> scanFiles(final String file) {

		System.out.println("The file : " + file);

		final String fileExt = "."+file.split("\\.")[1];

		System.out.println("The ext : " + fileExt);

		exeService = Executors.newCachedThreadPool();
		List<Future<FileScanResult>> values = null;

		System.out.println(illegalFileExtlist.toString());
		ArrayList<Callable<FileScanResult>> task = new ArrayList<>(illegalFileExtlist.size());

		for (String illegalExt : illegalFileExtlist) {

			illegalExt = illegalExt.trim();

			System.out.println("@@ illegalExt for processing " + illegalExt);
			task.add(new ExtTask(fileExt, file ,illegalExt));
		}

		try {
			values = exeService.invokeAll(task);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return values;
	}
}
