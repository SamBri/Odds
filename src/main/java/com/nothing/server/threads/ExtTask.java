package com.nothing.server.threads;

import java.util.concurrent.Callable;

import com.nothing.server.FileScannerResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExtTask implements Callable<FileScannerResponse> {

	
	private String fileExt;
	
	private String fileName;
	
	private String illegal;
	
	



	@Override
	public FileScannerResponse call() throws Exception {

		FileScannerResponse fileScanResult = new FileScannerResponse();
		
		if(!fileExt.equalsIgnoreCase(illegal))
		{
			fileScanResult.setFileName(fileName);
			fileScanResult.setFileExt(fileExt);
			fileScanResult.setResult("ACCEPTABLE");
			fileScanResult.setMessage(fileScanResult.getResult().concat(" ").concat("file ext : ").concat(fileScanResult.getFileExt()));
			
		}	else {
			
			fileScanResult.setFileName(fileName);
			fileScanResult.setFileExt(fileExt);
			fileScanResult.setResult("UNACCEPTABLE");
			fileScanResult.setMessage(fileScanResult.getResult().concat(" ").concat("file ext : ").concat(fileScanResult.getFileExt()).concat(" for file : ").concat(fileScanResult.getFileName()));
			
		} 
		

		return fileScanResult;

	}

}
