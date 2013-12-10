package parse.notifications;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import event.EventData;

public class Notifications {
	public static void main(String[] args) {

		HttpURLConnection con = null;
		
		String reqbody = "{\"channels\": [\"Testing\"]}";
		try {
			
			con = ConnectionUtilility.getHttpConnection("https://api.parse.com/1/installations/KpKuBtVCCB", "PUT");
			con.setRequestProperty("X-Parse-Application-Id", ApplicationData.APLICATION_ID.toString());
			con.setRequestProperty("X-Parse-REST-API-Key", ApplicationData.REST_API_KEY.toString());
			con.setRequestProperty("Content-Type", "application/json");
			
			DataOutputStream out = new DataOutputStream(con.getOutputStream());
			out.writeBytes(reqbody);
			out.flush();
			out.close();

			con.connect();
			
			getResponseData(con);
			
		} catch (Exception e) {
			System.out.println(e);
		}
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
		
	}
	
	public static void notifyChannel(EventData eventData) {
		
	}
	
}
