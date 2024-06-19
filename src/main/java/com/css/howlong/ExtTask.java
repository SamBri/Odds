package com.css.howlong;

import java.util.concurrent.Callable;

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
public class ExtTask implements Callable<FileScanResult> {

	
	private String fileExt;
	
	private String fileName;
	
	private String illegal;
	
	



	@Override
	public FileScanResult call() throws Exception {

		FileScanResult fileScanResult = new FileScanResult();
		
		if(!fileExt.equalsIgnoreCase(illegal))
		{
			fileScanResult.setFileExt(fileExt);
			fileScanResult.setResult("ACCEPTABLE");
			fileScanResult.setMessage(fileScanResult.getResult().concat(" ").concat("file ext : ").concat(fileScanResult.getFileExt()));
			
		}	else {
			
			fileScanResult.setFileExt(fileExt);
			fileScanResult.setResult("UNACCETABLE");
			fileScanResult.setMessage(fileScanResult.getResult().concat(" ").concat("file ext : ").concat(fileScanResult.getFileExt()));
			
		} 
		

		return fileScanResult;

	}

}
