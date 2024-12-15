package com.nothing.server.threads;

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
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import com.nothing.server.FileScannerResponse;
import com.nothing.server.entity.File;

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

	private List<File> list;

	@Value("#{'${LIST_OF_ILLEGAL_FILES_EXT}'.split(',')}")
	private List<String> illegalFileExtlist;

	private ExecutorService exeService;

	@Override
	public void run() {

		List<File> files = list;

		for (File file : list) {

			String theFile = file.getName();

			List<Future<FileScannerResponse>> values = scanFiles(theFile);
			
			
			values.forEach(System.out::println);

			for (Future<FileScannerResponse> future : values) {
				try {

					FileScannerResponse fileScanResult = future.get(); 
					
					if (!fileScanResult.getResult().equalsIgnoreCase("ACCEPTABLE")) {

						//emitter.send(fileScanResult.getMessage());
						System.err.println("@@@ sending message");
						
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

	private List<Future<FileScannerResponse>> scanFiles(final String file) {

		System.out.println("The file : " + file);

		final String fileExt = "."+file.split("\\.")[1];

		System.out.println("The ext : " + fileExt);

		exeService = Executors.newCachedThreadPool();
		List<Future<FileScannerResponse>> values = null;

		System.out.println(illegalFileExtlist.toString());
		ArrayList<Callable<FileScannerResponse>> task = new ArrayList<>(illegalFileExtlist.size());

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
