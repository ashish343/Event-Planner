package event;

import java.util.Map;

public class EventData {
	private String owner;
	private String eventId;
	private Map<String, String> contacts;
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	public Map<String, String> getContacts() {
		return contacts;
	}
	public void setContacts(Map<String, String> contacts) {
		this.contacts = contacts;
	}
}
