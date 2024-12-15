package com.nothing.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileScannerResponse {
	
	
  private String result;
  private String fileName;
  private String fileExt;
  private String message;  
  
  
	
	

}
