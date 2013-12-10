package database.mongo;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import event.EventData;

public class DataConnection {

    private static MongoClient mongoClient;
    private static DB db;
    private static DBCollection dbc;
    private static BasicDBObject query = new BasicDBObject();
    private static DBCursor cursor;
  
    public static void main(String args[]) throws UnknownHostException {
    	mongoClient = new MongoClient();
        db = mongoClient.getDB("dealsbytheway");
        dbc = db.getCollection("sellerInfo");
        System.out.println(dbc.toString());
    }
    
    public static void addEvent(EventData evetnData) throws UnknownHostException {
    	mongoClient = new MongoClient();
        db = mongoClient.getDB("dealsbytheway");
        dbc = db.getCollection("sellerInfo");
        System.out.println(dbc.toString());
    }
    
    public static void modifyEvent(String args[]) throws UnknownHostException {
    	mongoClient = new MongoClient();
        db = mongoClient.getDB("dealsbytheway");
        dbc = db.getCollection("sellerInfo");
        System.out.println(dbc.toString());
    }
}