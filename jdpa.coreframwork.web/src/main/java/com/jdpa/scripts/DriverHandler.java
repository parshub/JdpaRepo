package com.jdpa.scripts;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DriverHandler
{
	private static  WebDriver driver = null;
	
	public DriverHandler() throws IOException 
	{
		System.setProperty("webdriver.chrome.driver", "/home/himansu/Downloads/chromedriver");
		driver = new ChromeDriver(); 
		//driver = new FirefoxDriver();
	}

	public static WebDriver getDriver() throws IOException 
	{
		if (driver == null)
		{
		 new DriverHandler();
		}
		return driver;
	}
	
	public static void shutdown()
	{
		driver.quit();
	}
		 
	
}
