package com.jdpa.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jdpa.scripts.DriverHandler;
import com.jdpa.utilities.DBUtilities;
import com.jdpa.utilities.PageLocators;

public class Controller 
{	
	static WebDriver driver;
	static JsonServices jsonServiceObject = new JsonServices();
	static String key = null;
	static List<String> visitedList = new ArrayList<String>();
	List<String> listOfResponse = new ArrayList<String>(); 
	List<String> listOfnextKey = new ArrayList<String>(); 
	List<String> listOfoptionValue = new ArrayList<String>(); 
	List<String> listOfinputType = new ArrayList<String>();
	JSONObject travesalObject = null;
	JSONObject questionObject = null;
	DataConversion objectOfDataConversion = new DataConversion();
	Validator objectOfValidator = new Validator();
	DBUtilities objectOfDBUtilities = new DBUtilities();
		
	
	@BeforeTest
	public void init()throws IOException, ParseException
	{
		driver = DriverHandler.getDriver();
		driver.get("http://scripting.jdpoweronline.com/mrIWeb/mrIWeb.dll?I.Project=T1_QTYPE&i.test=1");
		driver.findElement(By.cssSelector("input.mrNext")).click();
		//travesalObject = jsonServiceObject.convertFileObjTOJsonObj("TraversalPath.json");
		//questionObject = jsonServiceObject.convertFileObjTOJsonObj("QuestionData.json");
		travesalObject = objectOfDBUtilities.fetchJSONObject("TraversalPath");
		questionObject = objectOfDBUtilities.fetchJSONObject("QuestionData");
	}
	
	@Test
	public void firstTest() 
	{	
		ArrayList arraylistObject = objectOfDataConversion.sortJsonAsPerKey(travesalObject);
		Controller objectOfEntry = new Controller();
		for (int i = 0; i < arraylistObject.size(); i++)
		{
			for (int j = 0; j <listOfnextKey.size(); j++)
			{
				key = listOfnextKey.get(j);
				if(objectOfValidator.validateList(visitedList , key))
					objectOfEntry.testRunninghelper(travesalObject, questionObject, key);
			}
			String key = (String) arraylistObject.get(i);
			if(objectOfValidator.validateList(visitedList , key))
				objectOfEntry.testRunninghelper(travesalObject, questionObject, key);
		}
	}
	
	
	
	
	public void testRunninghelper(JSONObject travesalObject , JSONObject questionObject , String key )
	{
		visitedList.add(key);
		String questionText = (String) ((JSONObject) questionObject.get(key)).get("Question");
		Validator validatorObject = new Validator();
		Boolean questionFlag = validatorObject.validateUI(driver , questionText);
		if(questionFlag)
		{
			PageLocators objpagelocator = new PageLocators();
			String  optionValue;
			listOfResponse = jsonServiceObject.traversalJsonContentReader(travesalObject, key , "Response");
			listOfnextKey = jsonServiceObject.traversalJsonContentReader(travesalObject, key , "next");
			listOfinputType = jsonServiceObject.questionJsonContentReader(questionObject, key, listOfResponse , "input");
			listOfoptionValue = jsonServiceObject.questionJsonContentReader(questionObject, key, listOfResponse , "value");
			if(listOfoptionValue != null)
			{
				String inputType = listOfinputType.get(0);
				if(inputType.equals("radio") || inputType.equals("checkbox"))
				{
					if(listOfoptionValue.size() == 1)
					{
						optionValue = listOfoptionValue.get(0);
						objpagelocator.getCheckBoxOrRadioButtonOnPage(driver, optionValue);
						objpagelocator.clickOnNext(driver);
					}
					else 
					{
						for (Iterator iterator = listOfoptionValue.iterator(); iterator.hasNext();)
						{
							optionValue = (String ) iterator.next();
							objpagelocator.getCheckBoxOrRadioButtonOnPage(driver, optionValue);
						}
						objpagelocator.clickOnNext(driver);
					}
				}
				else if(inputType.equals("text"))
				{
					System.out.println("in else part inputType -:"+inputType);
				}
				else
				{
					objpagelocator.clickOnNext(driver);
				}
			}
			else
			{
				System.out.println("..........Option is Not Present  ..!!!!!!!!!!!!!!");
			}
			
		}
		else
		{	
			System.out.println("Terminate ...!!!!!!!!!!!!!!");
			driver.quit();
		}
	}
	
}


