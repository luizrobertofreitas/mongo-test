package br.com.mongotest.model.dao;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MongoDaoTest extends TestCase {

	private MongoDao mongoDao;
	
	protected void setUp() throws Exception {
		mongoDao = new MongoDao();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testInsert() {
		BasicDBObject dbo = new BasicDBObject();
		dbo.put("name", "John Oracle");
		dbo.put("age", "30");
		dbo.put("email", "john@oracle.com");
		mongoDao.insert(dbo, "person");
		DBCursor categoriesCursor = mongoDao.findByQuery(dbo, "person");
		Assert.assertTrue(categoriesCursor.hasNext());
	}

	public void testUpdate() {
		DBObject dbo = mongoDao.findFirst("person");
		dbo.put("age", "32");
		mongoDao.update(dbo, "person");
		DBCursor categoriesCursor = mongoDao.findByQuery(dbo, "person");
		Assert.assertTrue(categoriesCursor.hasNext());
	}

	public void testFindAll() {
		DBCursor categoriesCursor = mongoDao.findAll("person");
		Assert.assertTrue(categoriesCursor.hasNext());
	}

	public void testFindByQuery() {
		BasicDBObject dbo = new BasicDBObject();
		dbo.put("name", "John Oracle");
		DBCursor categoriesCursor = mongoDao.findByQuery(dbo, "person");
		Assert.assertTrue(categoriesCursor.hasNext());
	}

	public void testFindByID() {
		DBCursor categoriesCursor = mongoDao.findAll("person");
		Assert.assertTrue(categoriesCursor.hasNext());
		DBObject dboQuery = categoriesCursor.next();
		DBObject dbo = mongoDao.findByID(dboQuery.get("_id"), "person");
		Assert.assertNotNull(dbo);
	}
	
	public void testFindFirst() {
		DBObject dbo = mongoDao.findFirst("person");
		Assert.assertNotNull(dbo);
	}
	
	public void testDelete() {
		BasicDBObject dbo = new BasicDBObject();
		dbo.put("name", "John Oracle");
		mongoDao.delete(dbo, "person");
		DBCursor categoriesCursor = mongoDao.findByQuery(dbo, "person");
		Assert.assertFalse(categoriesCursor.hasNext());
	}

}
