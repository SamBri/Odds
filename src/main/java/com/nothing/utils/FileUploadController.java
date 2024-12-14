package com.nothing.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/file")
@CrossOrigin(origins ="*") // allow all origins
public class FileUploadController {
	
	@Autowired
	FileExtensionVerificationTask fExtVtask;
	
	
	
	
	
	/**
	 * HTTP Streaming
	 * SSE Emitter
	 * Server Sent Events
	 * @return
	 */
	@RequestMapping(path="/scan", produces=MediaType.TEXT_EVENT_STREAM_VALUE ,method = {RequestMethod.GET,RequestMethod.POST})
	public SseEmitter scanFile(@RequestParam(name = "files", required=false) List<MultipartFile> files ) {
		
		SseEmitter emitter =  new SseEmitter(86_400_000L); // streaming length
		
		//System.out.println("incoming file @@@ " + file.getOriginalFilename());
		
		
		files.forEach((e)-> System.out.println("File : "+e.getOriginalFilename()));
		
		
		try {
			
			fExtVtask.setList(files);
			fExtVtask.setEmitter(emitter);
			Thread fileExtVerifyingThread = new Thread(fExtVtask);
			fileExtVerifyingThread.start();
		} catch(Exception e) {
			
			
		}
		

		return emitter;
	}
	
	
//	
//	@RequestMapping(path="/listen", produces=MediaType.TEXT_EVENT_STREAM_VALUE ,method = {RequestMethod.GET,RequestMethod.POST})
//	public String send(@RequestParam(name = "files", required=false) List<MultipartFile> files ) {
//		
//		
//		String response;
//		
//		 SseEmitter emitter =  scanFile(files);
//		 
//		 emitter.complete();
//		 
//		 emitter.onCompletion(()->{
//			 response = emitter.
//		 });
//		
//		
//	}
	
	
	
	/**
	 * HTTP Streaming
	 * SSE Emitter
	 * Server Sent Events
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

}
