package com.jdpa.services;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataConversion 
{
	public ArrayList sortJsonAsPerKey(JSONObject travesalObject)
	{
		Set setObject = (Set) travesalObject.keySet();
		SortedSet sortSets=new TreeSet<>(setObject);
		Iterator iteratorObject = sortSets.iterator();
		ArrayList arraylistObject = new ArrayList();
		while (iteratorObject.hasNext())
		{
		    String element = (String) iteratorObject.next();
			arraylistObject.add(element);
		}
		return arraylistObject;
	}
	
	
}
