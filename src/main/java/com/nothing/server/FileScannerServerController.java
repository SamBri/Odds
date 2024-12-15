package com.nothing.server;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.yaml.snakeyaml.util.EnumUtils;

import com.nothing.server.dao.FileScannerServerRepository;
import com.nothing.server.entity.File;
import com.nothing.server.threads.FileExtensionVerificationTask;
import com.nothing.server.utils.FileUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/file")
@CrossOrigin(origins = "*") // allow all origins
@Slf4j
public class FileScannerServerController {

	@Autowired
	FileExtensionVerificationTask fExtVtask;

	@Autowired
	FileScannerServerRepository fileScannerServerRepository;

	@RequestMapping(path = "/scan", produces = MediaType.APPLICATION_JSON_VALUE, method = { RequestMethod.POST })
	public RootResponse<List<String>> scanFile(
			@RequestParam(name = "files", required = true) List<MultipartFile> files) {

		final int count = files.size();
		List<String> responses = new ArrayList<>();
		String requestId = "FSC"
				.concat(String.valueOf(new Random().ints(1).map(Math::abs).findAny().getAsInt()).substring(0, 6));

		RootResponse<List<String>> serverResponse = new RootResponse<>();
		try {
			// 1. get files
			// 2. send files to File Scaning Server in memory database.

			files.forEach((e) -> {

				File tempFile = new File();
				tempFile.setName(e.getOriginalFilename());
				try {
					tempFile.setContent(new String(e.getBytes()));
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				tempFile.setFileId(UUID.randomUUID().toString());
				tempFile.setRequestId(requestId);
				tempFile.setSize(String.valueOf(e.getSize()));
				tempFile.setExtensionType(FileUtils.grabFileExtension(e.getOriginalFilename()));
				tempFile = fileScannerServerRepository.save(tempFile);

				if (tempFile != null) {
					responses.add("scanning file," + tempFile.getName());
				} else {
					responses.add("no scan performed for " + e.getOriginalFilename());
				}

			});

		} catch (Exception e) {

		}

		if (count == responses.size()) {
			String message = "files are being scanned";
			log.info(message);
			serverResponse.setCode(200);
			serverResponse.setResponse(responses);
			serverResponse.setTimestamp(OffsetDateTime.now());
			serverResponse.setStatus("SCANNING");
			serverResponse.setMessage(message);
			serverResponse.setRequestId(requestId);
		} else {
			log.info("not all files could be scanned");
		}

		return serverResponse;
	}

	@RequestMapping(path = "/scan/notification", produces = MediaType.APPLICATION_JSON_VALUE, method = {
			RequestMethod.GET })
	public List<File> emitFileScanNotification(@RequestParam String requestId) {

		SseEmitter emitter = new SseEmitter(86_400_000L); // streaming length

		Example<File> example = new Example<>() {

			@Override
			public File getProbe() {
				File exampleFile = new File();
				exampleFile.setRequestId(requestId);
				return exampleFile;

			}

			@Override
			public ExampleMatcher getMatcher() {
				return ExampleMatcher.matchingAll();
			}
		};

		List<File> files = fileScannerServerRepository.findAll(example)
				.stream()
				.map((File file)->{
					
				     File tempFile = file;
					 tempFile.setContent(null);
					 
					 return tempFile;
							
				}).toList();
		
	//	log.info("files size" +files.size());
		
		log.info("processing files for sse notifications.");
		

		try {
			fExtVtask.setList(files);
			fExtVtask.setEmitter(emitter);
			Thread fileExtVerifyingThread = new Thread(fExtVtask);
			fileExtVerifyingThread.start();
		} catch(Exception e) {
			
			
		}

		return files;
	}

	/**
	 * HTTP Streaming SSE Emitter Server Sent Events
	 * 
	 * @return
	 */
//	@RequestMapping(path="/scan", produces=MediaType.TEXT_PLAIN_VALUE,method = {RequestMethod.GET,RequestMethod.POST})
//	public CustomSseEmitter scanFile(@RequestParam("files") List<MultipartFile> files ) {
//		
//		CustomSseEmitter emitter =  new CustomSseEmitter(86_400_000L); // streaming length
//		
//		//System.out.println("incoming file @@@ " + file.getOriginalFilename());
//		
//		
//		files.forEach((e)-> System.out.println("File : "+e.getOriginalFilename()));
//		
//		
//		try {
//			
//			fExtVtask.setList(files);
//			fExtVtask.setEmitter(emitter);
//			Thread fileExtVerifyingThread = new Thread(fExtVtask);
//			fileExtVerifyingThread.start();
//		} catch(Exception e) {
//			
//			
//		}
//		
//
//		return emitter;
//	}
//	
//	

	public static void main(String[] args) {
		// new Random().ints(1).map(Math::abs).forEach(System.out::println);

		String requestId = "FSC"
				.concat(String.valueOf(new Random().ints(1).map(Math::abs).findAny().getAsInt()).substring(0, 6));

		System.out.println(requestId);
	}

}
