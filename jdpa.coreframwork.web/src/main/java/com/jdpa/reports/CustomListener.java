package com.jdpa.reports;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.jdpa.services.Controller;


public class CustomListener extends TestListenerAdapter 
{
	ReportData objReportData = new ReportData();
	Controller objeController = new Controller();
	
	@Override
	public void onTestFailure(ITestResult tr) {
		objReportData.setTestStep(tr.getName());
		objReportData.setStatus(tr.getStatus());
		objReportData.setQuetionID(objeController.getListquestionKey());
		objReportData.setResponse(objeController.getListquestionResponce());
		objReportData.setActualData(objeController.getQuestion());
		try {
			objReportData.createJsonFileForTestReoprt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		objReportData.setTestStep(tr.getName());
		objReportData.setStatus(tr.getStatus());
		objReportData.setQuetionID(objeController.getListquestionKey());
		objReportData.setResponse(objeController.getListquestionResponce());
		objReportData.setActualData(objeController.getQuestion());
		try {
			objReportData.createJsonFileForTestReoprt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		objReportData.setTestStep(tr.getName());
		objReportData.setStatus(tr.getStatus());
		objReportData.setQuetionID(objeController.getListquestionKey());
		objReportData.setResponse(objeController.getListquestionResponce());
		objReportData.setActualData(objeController.getQuestion());
		try {
			objReportData.createJsonFileForTestReoprt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}






























	/*JSONObject obj = new JSONObject();
	
	obj.put("Survey Track Test", "");			//its Depend on Status is Pass or Fail
	obj.put("Test Survey Name", "");			// Survey Name
	
	obj.put("Test Step", "1");					// Test Method
	obj.put("Question", "Q1");					//Quetion Key

	JSONArray response = new JSONArray();
	response.add("eBay");						//reponse List Iterator
	response.add("Paypal");
	obj.put("Response List", response);
	
	
	obj.put("Expected","");						// Pass
	obj.put("Actual","");						// Thoes We Got
	obj.put("Status", "pass");					// Thoes We Got if status is 1 its mean Pass else Fail
	
	FileWriter file = new FileWriter("Report.json");
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
	}
}*/





/*
 * System.out.println("onTestSuccess ------"+testCaseName);
 * System.out.println("onTestSuccess  -------Start mills------"+
 * tr.getStartMillis());
 * System.out.println("onTestSuccess  -----End Mills--------"+
 * tr.getEndMillis()); System.out.println("onTestSuccess  -------Status------"+
 * tr.getStatus()); long startTime = tr.getStartMillis(); long endTime =
 * tr.getEndMillis(); long totaltime = endTime - startTime ;
 * System.out.println("Total Time In Second "+ totaltime/1000);
 * System.out.println("Get Instance Name"+tr.getInstanceName());
 * System.out.println("is Success "+tr.isSuccess());
 * System.out.println("Get Test Class"+tr.getClass());
 * Reporter.log(testCaseName); Reporter.log(get_random_string()); List<String>
 * reporterOutput = Reporter.getOutput(tr);
 * System.out.println(reporterOutput.size()); for (String string :
 * reporterOutput) { System.out.println(string); }
 */

/*
 * private void log(String string) { System.out.print(string); if (++m_count %
 * 40 == 0) { System.out.println(""); } }
 * 
 * public static String get_random_string(){ char[] CHARSET_AZ_09 =
 * "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray(); Random random = new
 * SecureRandom(); char[] result = new char[23]; for (int num = 0; num <
 * result.length; num++) { int randomCharIndex =
 * random.nextInt(CHARSET_AZ_09.length); result[num] =
 * CHARSET_AZ_09[randomCharIndex]; } return new String(result); }
 */

/*
 * private void log(String string) { System.out.print(string); if (++m_count %
 * 40 == 0) { System.out.println(""); } }
 * 
 * public static String get_random_string(){ char[] CHARSET_AZ_09 =
 * "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray(); Random random = new
 * SecureRandom(); char[] result = new char[23]; for (int num = 0; num <
 * result.length; num++) { int randomCharIndex =
 * random.nextInt(CHARSET_AZ_09.length); result[num] =
 * CHARSET_AZ_09[randomCharIndex]; } return new String(result); }
 */
