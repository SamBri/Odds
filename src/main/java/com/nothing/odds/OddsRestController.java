package com.nothing.odds;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Console;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import java.nio.file.Files;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.List;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import com.nothing.CommandLineReader;
import com.nothing.exception.HowLongException;

@RestController

@RequestMapping("/odds")

@CrossOrigin(origins = "*") // enable cross origin on the controller level of therapists ms for all origins

// with defaults settings

public class OddsRestController {

	@GetMapping(path = "/console", produces = "application/octet-stream")

	public ResponseEntity<Console> getConsole() {

		Console console = System.console();

		if (console == null) {

			throw new RuntimeException("Console not available!");

			// System.err.println("Console not available!");

		} else {

//		

//		   String name = console.readLine("Please enter a text:");

//			

//		   console.format("You entered %s ", name);

//		

//		   console.writer().println();

		}

		return ResponseEntity.ok(console);

	}

	@GetMapping(path = "/console3", produces = "application/octet-stream")
	public ResponseEntity<Console> getConsole3() {

		Console console = System.console();

		if (console == null) {

			throw new RuntimeException("Console not available!");

			// System.err.println("Console not available!");

		} else {

// 	

// 		   String name = console.readLine("Please enter a text:");

// 			

// 		   console.format("You entered %s ", name);

// 		

// 		   console.writer().println();

		}

		HttpHeaders clseHeaders = new HttpHeaders();

		clseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		ResponseEntity<Console> clse = new ResponseEntity<>(console, clseHeaders, HttpStatus.OK);

		return clse;

	}

	@GetMapping("/console2")

	public ResponseEntity<String> getConsole2() {

		File cmdFile = new File("C:\\WINDOWS\\system32\\cmd.exe");

		try (var in = new BufferedInputStream(new FileInputStream(cmdFile))) {

			var buffer = new byte[1024];

			int numBytesRead = 0;

			while ((numBytesRead = in.read(buffer)) > 0) {

				System.out.println(numBytesRead);

			}

		} catch (IOException e) {

		}

		return ResponseEntity.ok("okay");

	}

	@GetMapping(path = "/command-line", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> runCommandLine() {

		File outFile = new File("cmd-out.txt");

		List<String> cmdList = null;

		StringBuilder consoleOutput = null;
		try {

			ProcessBuilder cmdProcess = new ProcessBuilder("cmd.exe");

			cmdProcess.redirectOutput(outFile);


			Process cProcess = cmdProcess.start();
			
			
			boolean d = cProcess.waitFor(10, TimeUnit.SECONDS);
			
			System.out.println(d);
			
	
			BufferedReader reader = new BufferedReader(new FileReader(outFile));

			String s;
			consoleOutput = new StringBuilder();
			while ((s = reader.readLine()) != null) {

				System.out.println("inside loop :: " + s);
				consoleOutput.append(s);

			} 
			System.out.println("done ");

			reader.close();
		} catch (IOException e) {

			System.out.println("Exception :: " + e);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);

		return ResponseEntity.ok().headers(headers).body(consoleOutput);

	}

	
	@GetMapping(value = {"/how-long/since-created","/how-long/estimated-ahead"})
	public ResponseEntity<HowLongResponse> checkHowLong(@RequestParam String submittedDateTime,HttpServletRequest theRequest) 
{

		String howLong = null;
		try {

		   String howLongPath =	theRequest.getRequestURI().toString();
			System.out.println("The howLongPath intercepted :: {}".replace("{}", howLongPath));
			ZonedDateTime theParsedSubmittedDateTime  = ZonedDateTime.parse(submittedDateTime) ;
			ZonedDateTime today = ZonedDateTime.now();
			Duration theDuration = Duration.between(today, theParsedSubmittedDateTime);

			howLong = MyDateUtils.parsePostDuration(theDuration,howLongPath);
			

		} catch (HowLongException e) {

			throw new HowLongException(e);
		}

		return ResponseEntity.ok(new HowLongResponse(howLong));

	}

	
	
	
	
	
	
	public static void main(String[] args) {

		File outFile = new File("out.txt");

		try (BufferedOutputStream cmdOutFile = new BufferedOutputStream(new FileOutputStream(outFile))) {

			ProcessBuilder cmdProcess = new ProcessBuilder("C:\\WINDOWS\\system32\\cmd.exe", "java", "-version");

			cmdProcess.redirectOutput(outFile);

			cmdProcess.start();

//			OutputStream cmdOutStream = cmdProcess.getOutputStream();

//		
//
			int b;
//
////
//
			var buffer = new byte[1024];
//
////
//
			int numBytesRead = 0;
//
////			
//
////			
//
////			
//
//			while ((numBytesRead = cmdInStream.read(buffer)) > 0 ) {
//
//
//
//				System.out.println("reading input");
//
//				System.out.println(numBytesRead);
//
//				cmdOutFile.write(buffer, 0, numBytesRead)	;			
//
//
//			cmdOutFile.flush();
//
//				System.out.println("reading input@@");
//
////
//
//			}

//			   while((b = cmdInStream.read()) != -1) {

//				   

//				   cmdOutFile.write(b);

//			   }

//			   

			// int done = cmdProcess.exitValue();

			System.out.println("out of the loop");

			// cmdList = Files.readAllLines(outFile.toPath());

		} catch (IOException e) {

			System.out.println("Exception :: " + e);

		}

	}

}
