package com.jdpa.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class PageLocators 
{
	WebElement elementLocator = null;
	String jQuery;
	
	public void getCheckBoxOrRadioButtonOnPage(WebDriver driver, String locatorText)
	{
		jQuery = "return    $(\"label:contains('" + locatorText + "')\" ).closest('span').find('input')[0]";
		JavascriptExecutor jse = (JavascriptExecutor)driver; 
		jse=(JavascriptExecutor)driver;
		WebElement elementLocator=(WebElement)jse.executeScript(jQuery);
		elementLocator.click();
		//Reporter.log(locatorText+" passed");
	}
	
	public void clickOnNext(WebDriver driver)
	{
		//Reporter.log(locatorText+" >> passed");
		driver.findElement(By.cssSelector("input.mrNext")).click();
		
	}
	
	
}

