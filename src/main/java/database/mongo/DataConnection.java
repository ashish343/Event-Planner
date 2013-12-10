package database.mongo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import event.EventData;

public class DataConnection {

	private final MongoClient mongoClient;
	private String mongoURI;
	private String db;
	private static DB mongoDb;
	private static BasicDBObject query = new BasicDBObject();
	private static DBCursor cursor;

	private DataConnection() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("config"));
		String line = null;

		while ((line = br.readLine()) != null) {
			String[] arr = line.split(" ");
			switch (arr[0]) {
			case "uri":
				mongoURI = arr[1];
				break;
			case "db":
				db = arr[1];
				break;
			}
		}
		br.close();
		if (mongoURI == null || db == null) {
			System.err
					.println("Connection Information or DB information not present.. Exiting");
			System.exit(1);
		}
		mongoClient = new MongoClient(new MongoClientURI(mongoURI));
		mongoDb = mongoClient.getDB(db);

	}

	public static DBCollection getCollection(String collection)
			throws IOException {
		if (mongoDb == null)
			new DataConnection();
		return mongoDb.getCollection(collection);
	}

	public static void addEvent(EventData evetnData) throws IOException {
		if (mongoDb == null)
			new DataConnection();
		DBCollection dbc = mongoDb.getCollection("sellerInfo");
	}

	public static void modifyEvent(String args[]) throws UnknownHostException {
		Object dbc = mongoDb.getCollection("sellerInfo");
		System.out.println(dbc.toString());
	}

	public static void main(String[] args) throws IOException {
		new DataConnection();
		System.out.println(mongoDb);
	}
}