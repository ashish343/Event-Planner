package com.database.mongo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.contacts.Contact;
import com.event.EventData;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;

public class DataConnection {

	private final MongoClient mongoClient;
	private String mongoURI;
	private String db;
	private static DB mongoDb;
	private static String EVENTABLE;
	private static String USERTABLE;

	private static DBCollection event;
	private static DBCollection user;

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
			case "event_table":
				EVENTABLE = arr[1];
				break;
			case "user_table":
				USERTABLE = arr[1];
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
		System.out.println(mongoDb.collectionExists(EVENTABLE));
		event = mongoDb.getCollection(EVENTABLE);
		user = mongoDb.getCollection(USERTABLE);
	}

	public static DBCollection getCollection(String collection)
			throws IOException {
		if (mongoDb == null)
			new DataConnection();
		return mongoDb.getCollection(collection);
	}

	public static void addEvent(EventData eventData) throws IOException {
		if (mongoDb == null)
			new DataConnection();

		BasicDBObject bobj = new BasicDBObject(EventData.OWNER,
				eventData.getOwner()).append(EventData.EVENTID,
				eventData.getEventId());
		StringBuffer sb = new StringBuffer();
		List<Contact> cList = eventData.getContacts();
		BasicDBList bdbl = new BasicDBList();
		for (int ix = 0; ix < cList.size(); ix++) {
			bdbl.add(cList.get(ix).getUid());
		}

		bobj.append(EventData.INVITES, bdbl);
		bobj.append(EventData.ACCEPTED, new BasicDBList());
		bobj.append(EventData.DECLINED, new BasicDBList());
		event.insert(bobj);
	}

	public static void modifyEvent(String eventId, String contactUID,
			boolean accept) throws IOException {
		if (mongoDb == null)
			new DataConnection();
		String update = null;
		if (accept) {
			update = "{$push:{" + EventData.ACCEPTED + ":'" + contactUID
					+ "'}}";
		} else {
			update = "{$push:{" + EventData.DECLINED + ":'" + contactUID
					+ "'}}";
		}
		BasicDBObject query = new BasicDBObject(EventData.EVENTID, eventId);
		event.update(query, (DBObject) JSON.parse(update));
	}

	public static void main(String[] args) throws IOException {
		// test
		DataConnection.modifyEvent("1", "2", true);
	}
}