package com.jdpa.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonServices
{
	public JSONObject convertFileObjTOJsonObj(String filename) 
	{
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null ;
		try 
		{
			Object obj = parser.parse(new FileReader(filename));
			jsonObject =  (JSONObject) obj;
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	public List<String> traversalJsonContentReader(JSONObject tempjsonObject , String key , String internalKey)
	{
		List<String> listOfResponse = new ArrayList<String>();
		int nuberOfResponse = (int) ((JSONArray)((JSONObject)tempjsonObject.get(key)).get(internalKey)).size();
		if(nuberOfResponse > 1)
		{
			for (int i = 0; i < nuberOfResponse; i++)
				listOfResponse.add((String) ((JSONArray)((JSONObject)tempjsonObject.get(key)).get(internalKey)).get(i));
			
		}
		else
		{
			String respo = (String) ((JSONArray)((JSONObject)tempjsonObject.get(key)).get(internalKey)).get(0);
			listOfResponse.add(respo);
		}
		return listOfResponse;
	}
	
	public List<String> questionJsonContentReader(JSONObject questionObject , String key , List<String> listOfResponse , String value)
	{
		String respo = null;
		List<String> listOfResponseMatched = new ArrayList<String>();
		String response;
		for (int i = 1; i < ((JSONObject)questionObject.get(key)).size(); i++) 
		{
			if(value == "value")
			{	
				respo = (String) ((JSONObject)((JSONObject)questionObject.get(key)).get(""+i+"")).get(value);
				for (int j = 0; j < listOfResponse.size(); j++) 
				{
					response = (String) listOfResponse.get(j);
					if(respo.equals(response)) 
					{	
						listOfResponseMatched.add(response);
						break;
					}	
				}
			}
			else
			{	
				listOfResponseMatched.add((String) ((JSONObject)((JSONObject)questionObject.get(key)).get(""+i+"")).get(value));
			}
		}
		return listOfResponseMatched;
	}
	
	
	
}


