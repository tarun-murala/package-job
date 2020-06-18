package com.tarun;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UITest {
	
	private WebDriver driver;		
	
    @Test				
	public void testServiceNow() {	
		driver.get("http://servicenow.com");  
		String title = driver.getTitle();				 
		AssertJUnit.assertTrue(title.contains("ServiceNow")); 		
	}	
	
    @BeforeTest
	public void beforeTest() {	
	    driver = new FirefoxDriver();  
	}		
	
    @AfterTest
	public void afterTest() {
		driver.quit();			
	}	
}
