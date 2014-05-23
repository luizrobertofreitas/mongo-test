package br.com.mongotest.model.dao;

import br.com.mongotest.model.config.MongoConnectionFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MongoDao {

	private DB db;
	
	public MongoDao() {
		db = MongoConnectionFactory.getMongoDB();
	}
	
	public void insert(DBObject dbo, String collectionName) {
		DBCollection dbColl = db.getCollection(collectionName);
		dbColl.insert(dbo);
	}
	
	public void update(DBObject dbo, String collectionName) {
		DBCollection dbColl = db.getCollection(collectionName);
		dbColl.save(dbo);
	}
	
	public void delete(DBObject dbo, String collectionName) {
		DBCollection dbColl = db.getCollection(collectionName);
		dbColl.remove(dbo);
	}
	
	public DBCursor findAll(String collectionName) {
		DBCollection dbColl = db.getCollection(collectionName);
		return dbColl.find();
	}
	
	public DBCursor findByQuery(DBObject dboQuery, String collectionName) {
		DBCollection dbColl = db.getCollection(collectionName);
		return dbColl.find(dboQuery);
	}
	
	public DBObject findByID(Object _id, String collectionName) {
		DBCollection dbColl = db.getCollection(collectionName);
		return dbColl.findOne(new BasicDBObject("_id", _id));
	}
	
	public DBObject findFirst(String collectionName) {
		DBCollection dbColl = db.getCollection(collectionName);
		return dbColl.findOne();
	}
}
