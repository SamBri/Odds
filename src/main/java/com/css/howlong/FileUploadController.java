package com.css.howlong;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/file")
public class FileUploadController {
	
	@Autowired
	FileExtensionVerificationTask fExtVtask;
	
	
	
	
	
	/**
	 * HTTP Streaming
	 * SSE Emitter
	 * Server Sent Events
	 * @return
	 */
	@PostMapping(path="/scan", produces=MediaType.MULTIPART_FORM_DATA_VALUE)
	public SseEmitter scanFile(@RequestParam("files") List<MultipartFile> files ) {
		
		SseEmitter emitter = new SseEmitter(86_400_000L); // streaming length
		
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
	
	

	

}
