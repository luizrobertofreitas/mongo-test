package br.com.mongotest.model.config;
import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;


public class MongoConnectionFactory {

	private static MongoClient mongoClient;
	
	static {
		try {
			mongoClient = new MongoClient("localhost");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public static DB getMongoDB() {
		return mongoClient.getDB("mongo-test");
	}
	
	public static void close() {
		if (mongoClient != null) {
			mongoClient.close();
		}
	}
	
}
