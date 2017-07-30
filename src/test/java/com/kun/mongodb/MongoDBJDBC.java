package com.kun.mongodb;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBJDBC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
		System.out.println("Connect to database successfully");
		MongoCollection<Document> collection = mongoDatabase.getCollection("test");
		
	}

}
