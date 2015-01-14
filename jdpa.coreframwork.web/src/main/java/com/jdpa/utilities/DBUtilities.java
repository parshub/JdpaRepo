package com.jdpa.utilities;

import java.net.UnknownHostException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class DBUtilities
{
	JSONParser parser = new JSONParser();
	public JSONObject fetchJSONObject(String collectionName) throws ParseException	
	{	
		JSONObject jsonQnData = null;
		try 
		{
			MongoClient mongo;
			mongo = new MongoClient("localhost", 27017);
			DB db = mongo.getDB("jdpa");
			DBCollection collection = db.getCollection(collectionName);
			DBCursor curser = collection.find();
			String str = curser.next().toString();
			jsonQnData = (JSONObject) parser.parse(str);
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		} 
		catch (MongoException e) {
			e.printStackTrace();
		}
		return jsonQnData;
	}
}