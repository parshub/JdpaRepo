package com.jdpa.reports;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ReportData {
	String surveyTrackTest;
	String testSurveyName;
	String expectedData;
	static List<String>  testStep = new ArrayList<String>();
	List<String> actualData = new ArrayList<String>();
	List<Integer> status = new  ArrayList<Integer>();
	List<String> listquetionId = new ArrayList<String>();
	List<List<String>> listResponce = new ArrayList<List<String>>();

	JSONArray objJsonArray = new JSONArray();
	String tempStatus = "Not Verfied";
	String tempActual = null;
	File file =new File("/jdpa.coreframwork.web/Report.json");
	
	
	public ReportData() {

	}

	public void setSurveyTrackTest(String surveyTrackTest) {
		this.surveyTrackTest = surveyTrackTest;
	}

	public void setTestSurveyName(String testSurveyName) {
		this.testSurveyName = testSurveyName;
	}

	public void setTestStep(String testStep){
		this.testStep.add(testStep);
	}

	public void setQuetionID(List<String> listquetionId) {
		this.listquetionId = listquetionId;
	}

	public void setResponse(List<List<String>> listResponce) {
		this.listResponce = listResponce;
	}

	public void setExpectedData(String expectedData) {
		this.expectedData = expectedData;
	}

	public void setActualData(List<String> actualData) {
		this.actualData = actualData;
	}

	public void setStatus(int status) {
		this.status.add(status);
	}

	public String getSurveyTrackTest() {
		return surveyTrackTest;
	}

	public String getTestSurveyName() {
		return testSurveyName;
	}

	public List<String> getTestStep() {
		return testStep;
	}

	public List<String> getQuetionID() {
		return listquetionId;
	}

	public List<List<String>> getResponse() {
		return listResponce;
	}

	public String getExpectedData() {
		return expectedData;
	}

	public List<String> getActualData() {
		return actualData;
	}

	public List<Integer> getStatus() {
		return status;
	}

	
	public void createJsonFileForTestReoprt() throws IOException 
	{
		FileWriter fileWritter = new FileWriter(file.getName(),true);
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			
		for (int i = 0; i < listquetionId.size(); i++)
		{
			JSONObject objJson = new JSONObject();
			if(status.get(0) == 1)
				tempStatus = "Passed";
			else if(status.get(0) == 2)
				tempStatus = "Failed" ;
			else 
				tempStatus = "Skipped" ;
			surveyTrackTest = tempStatus;
			objJson.put("surveyTrackTest ",surveyTrackTest);
			objJson.put("testSurveyName ", "TraversalPath.json");
			objJson.put("testStep ",testStep.get(0));
			objJson.put("Questio ID ", listquetionId.get(i));
			objJson.put("listResponce",  listResponce.get(i));
		    if(actualData.get(i).equals("true"))
		    	tempActual = "Verified";
		    objJson.put("Actual ", tempActual);
		    objJson.put("Expected ","Verified" );
		    objJson.put("Status ", tempStatus);
		    objJsonArray.add(objJson);
		}
		bufferWritter.write(objJsonArray.toJSONString());
		bufferWritter.close();
	}
}


/*for (int i = 0; i < listquetionId.size(); i++)
{
	if(status.get(i) == 1)
		tempStatus = "Passed";
	else if(status.get(i) == 2)
		tempStatus = "Failed" ;
	else 
		tempStatus = "Skipped" ;
	surveyTrackTest = tempStatus;
	obj.put("surveyTrackTest", surveyTrackTest);
    obj.put("testSurveyName", "TraversalPath.json ");
    obj.put("testStep", testStep.get(i));
    obj.put("Questio ID", listquetionId.get(i));
    JSONArray responce = new JSONArray();
    responce.add(listResponce.get(i));
    obj.put("Response", responce);
    if(actualData.get(i).equals("true"))
    	tempActual = "Verified";
    obj.put("Actual", tempActual);
    obj.put("Expected","Verified" );
    obj.put("Status", status.get(i));
}

System.out.println("final ...-----"+obj.toJSONString());*/
/*FileWriter file = new FileWriter("Report.json");
try 
{
	file.write(obj.toJSONString());
    System.out.println("Successfully Copied JSON Object to File...");
    System.out.println("\nJSON Object: " + obj);
}
catch (IOException e) 
{
     e.printStackTrace(); 
}
finally 
{
	file.flush();
    file.close();
}*/
	
