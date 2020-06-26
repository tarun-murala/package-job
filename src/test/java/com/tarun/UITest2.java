package com.tarun;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UITest2 {
	
	private FirefoxDriver driver;		
	
    @Test				
	public void testServiceNow() {	
		driver.get("http://servicenow.com");  
		String title = driver.getTitle();				 
		AssertJUnit.assertTrue(true); 		
	}	
	
    @BeforeTest
	public void beforeTest() {
    		File pathToBinary = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
    		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
    		FirefoxProfile firefoxProfile = new FirefoxProfile();
    		driver = new FirefoxDriver(ffBinary,firefoxProfile);  
	}		
	
    @AfterTest
	public void afterTest() {
		driver.quit();			
	}	
}
