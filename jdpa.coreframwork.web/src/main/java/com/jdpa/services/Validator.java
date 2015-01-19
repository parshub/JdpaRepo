package com.jdpa.services;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Validator 
{
	public Boolean validateUI(WebDriver driver ,String questionText)
	{
		JavascriptExecutor jse;
		Boolean flag = false;
		String jQuery = "return $(\"span:contains('"+questionText+"')\").after($('script')).text()";
		jse = (JavascriptExecutor)driver;
		String elementLocator=jse.executeScript(jQuery).toString();
		if(questionText.equals(elementLocator))
		{
			flag = true;
		}
		else
			System.out.println("UI is not Valid ....!!!!!!!");
		return flag;
	}
	
	public Boolean validateList(List<String> visitedList , String key)
	{
		Boolean flag = false;
		if(visitedList.contains(key))
			flag = false;
		else
			flag = true;
		return flag;
	}
}
