package parse.notifications;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import contacts.Contact;
import event.EventData;

public class Notifications {
	public static void main(String[] args) {

//		HttpURLConnection con = null;
//		
//		String reqbody = "{\"channels\": [\"Testing\"]}";
		try {
			testNotifyChannel();
//			con = ConnectionUtilility.getHttpConnection("https://api.parse.com/1/installations/KpKuBtVCCB", "PUT");
//			initializeConnection(con);
//			
//			DataOutputStream out = new DataOutputStream(con.getOutputStream());
//			out.writeBytes(reqbody);
//			out.flush();
//			out.close();
//
//			con.connect();
//			
//			getResponseData(con);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	
	private static void initializeConnection(HttpURLConnection con) {
		con.setRequestProperty("X-Parse-Application-Id", ApplicationData.APLICATION_ID.toString());
		con.setRequestProperty("X-Parse-REST-API-Key", ApplicationData.REST_API_KEY.toString());
		con.setRequestProperty("Content-Type", "application/json");
	}


	private static void getResponseData(HttpURLConnection con) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		String temp = null;
		
		StringBuilder sb = new StringBuilder();
		while ((temp = in.readLine()) != null) {
			sb.append(temp).append(" ");
		}
		String result = sb.toString();
		System.out.println("Result is:" + result);
		in.close();
	}
	
	public static void registerChannel(EventData eventData) {
		HttpURLConnection con = null;
		String eventId = eventData.getEventId();
		String reqbody = "{\"channels\": [\""+  eventId + "\"]}";
		
		List<Contact> contacts = eventData.getContacts();
			
		for(Contact contact : contacts) {
			try {
				String objectId = contact.getUid();
				con = ConnectionUtilility.getHttpConnection(RestApi.PARSE_HOST.toString() + RestApi.INSTALLATION.toString() + "/" + objectId, "PUT");
				initializeConnection(con);
				
				DataOutputStream out = new DataOutputStream(con.getOutputStream());
				out.writeBytes(reqbody);
				out.flush();
				out.close();
				con.connect();
				//TODO: No need to print.
				getResponseData(con);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	private static void testRegisterChannel() {
		EventData eventData = new EventData();
		List<Contact> contacts = new ArrayList<Contact>();
		Contact contact = new Contact();
		contact.setUid("KpKuBtVCCB");
		contacts.add(contact);
		
		eventData.setContacts(contacts);
		eventData.setEventId("Temp");
		registerChannel(eventData);
	}
	
	public static void notifyChannel(EventData eventData) {
		HttpURLConnection con = null;
		String Message = "New Even has been created";
		String channel = eventData.getEventId();
		String msg = "{\"channels\":[\"" + channel + "\"],\"data\": {\"alert\":\""+ Message + "\"}}";
		
		List<Contact> contacts = eventData.getContacts();
		
		for(Contact contact : contacts) {
			try {
				String objectId = contact.getUid();
				con = ConnectionUtilility.getHttpConnection(RestApi.PARSE_HOST.toString() + RestApi.PUSH.toString(), "POST");
				initializeConnection(con);
				
				DataOutputStream out = new DataOutputStream(con.getOutputStream());
				out.writeBytes(msg);
				out.flush();
				out.close();
				con.connect();
				//TODO: No need to print.
				getResponseData(con);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	private static void testNotifyChannel() {
		EventData eventData = new EventData();
		List<Contact> contacts = new ArrayList<Contact>();
		Contact contact = new Contact();
		contact.setUid("KpKuBtVCCB");
		contacts.add(contact);
		eventData.setEventId("Temp");
		eventData.setContacts(contacts);
		notifyChannel(eventData);
	}
	
}
