package com.jdpa.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jdpa.reports.ReportData;
import com.jdpa.scripts.DriverHandler;
import com.jdpa.utilities.DBUtilities;
import com.jdpa.utilities.PageLocators;

public class Controller {
	static WebDriver driver;
	static JsonServices jsonServiceObject = new JsonServices();
	static String key = null;
	List<String> listOfResponse = new ArrayList<String>();
	static List<String> visitedList = new ArrayList<String>();

	List<String> listOfnextKey = new ArrayList<String>();
	List<String> listOfoptionValue = new ArrayList<String>();
	List<String> listOfinputType = new ArrayList<String>();
	JSONObject travesalObject = null;
	JSONObject questionObject = null;
	DataConversion objectOfDataConversion = new DataConversion();
	Validator objectOfValidator = new Validator();
	DBUtilities objectOfDBUtilities = new DBUtilities();
	
	static List<String> listquestionKey = new ArrayList<String>();
	
	public static List<String> getListquestionKey() {
		return listquestionKey;
	}


	public static void setListquestionKey(List<String> listquestionKey) {
		Controller.listquestionKey = listquestionKey;
	}

	static List<List<String>> listquestionResponce = new ArrayList<List<String>>();
	
	public static List<List<String>> getListquestionResponce() {
		return (List<List<String>>) listquestionResponce;
	}


	public static void setListquestionResponce(List<List<String>> listquestionResponce) {
		Controller.listquestionResponce = listquestionResponce;
	}


	
	
	static List<String> listquestionFlag = new ArrayList<String>();
	public List<String> getQuestion() {
		return listquestionFlag;
	}
	

	@BeforeTest
	public void init() throws IOException, ParseException {
		driver = DriverHandler.getDriver();
		driver.get("http://scripting.jdpoweronline.com/mrIWeb/mrIWeb.dll?I.Project=T1_QTYPE&i.test=1");
		driver.findElement(By.cssSelector("input.mrNext")).click();
		travesalObject = jsonServiceObject
				.convertFileObjTOJsonObj("TraversalPath.json");
		questionObject = jsonServiceObject
				.convertFileObjTOJsonObj("QuestionData.json");
		// travesalObject =
		// objectOfDBUtilities.fetchJSONObject("TraversalPath");
		// questionObject = objectOfDBUtilities.fetchJSONObject("QuestionData");

	}

	@Test
	public void firstTest() {
		ArrayList arraylistObject = objectOfDataConversion
				.sortJsonAsPerKey(travesalObject);
		Controller objectOfEntry = new Controller();
		for (int i = 0; i < arraylistObject.size(); i++) {
			for (int j = 0; j < listOfnextKey.size(); j++) {
				key = listOfnextKey.get(j);
				if (objectOfValidator.validateList(visitedList, key))
					objectOfEntry.testRunninghelper(travesalObject,
							questionObject, key);
			}
			String key = (String) arraylistObject.get(i);
			if (objectOfValidator.validateList(visitedList, key))
				objectOfEntry.testRunninghelper(travesalObject, questionObject,
						key);
		}
	}

	
	public void testRunninghelper(JSONObject travesalObject, JSONObject questionObject, String key) 
	{
		visitedList.add(key);
		String questionText = (String) ((JSONObject) questionObject.get(key)).get("Question");
		Validator validatorObject = new Validator();
		Boolean questionFlag = validatorObject.validateUI(driver, questionText);
		if (questionFlag) {
			listquestionFlag.add("true");
			PageLocators objpagelocator = new PageLocators();
			String optionValue = null;
			listOfResponse = jsonServiceObject.traversalJsonContentReader(travesalObject, key, "Response");
			listOfnextKey = jsonServiceObject.traversalJsonContentReader(travesalObject, key, "next");
			listOfinputType = jsonServiceObject.questionJsonContentReader(questionObject, key, listOfResponse, "input");
			listOfoptionValue = jsonServiceObject.questionJsonContentReader(questionObject, key, listOfResponse, "value");
			if (listOfoptionValue != null)
			{
				String inputType = listOfinputType.get(0);
				if (inputType.equals("radio") || inputType.equals("checkbox"))
				{
					for (Iterator iterator = listOfoptionValue.iterator(); iterator.hasNext();) {
						optionValue = (String) iterator.next();
						objpagelocator.getCheckBoxOrRadioButtonOnPage(driver,
								optionValue);
					}
					objpagelocator.clickOnNext(driver);
				}
			} 
			else 
			{
				System.out.println("..........Option is Not Present  ..!!!!!!!!!!!!!!");
			}

		}
		else {
			listquestionFlag.add("False");
			System.out.println("Terminate ...!!!!!!!!!!!!!!");
			// driver.quit();
		}
		listquestionKey.add(key);
		listquestionResponce.add(listOfResponse);
		//objHashMap.put(key, listOfResponse);
	}
	
	
}
