package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Credential {
	
	private String baseUrl;
	private String authToken;
	
	 public String getBaseUrl() {
	        return baseUrl;
	  }
	 
	 public String getAuthToken() {
	        return authToken;
	  }
	 
	 public Credential() {
		 String propFileName = "config.properties";
	        Properties confInfo = new Properties();
	        
	        try (InputStream inStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {
	            confInfo.load(inStream);
	          } catch (IOException e) {
	            e.printStackTrace();
	          }
	        
	        this.authToken=confInfo.getProperty("authToken");
	        this.baseUrl=confInfo.getProperty("baseUrl");
	 }

}
