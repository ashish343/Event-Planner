package event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import contacts.Contact;
import database.mongo.DataConnection;

public class EventData {
	private String owner;
	private String eventId;
	private List<Contact> contacts;
	public static final String OWNER = "owner";
	public static final String EVENTID = "eventID";
	public static final String INVITES = "invites";
	public static final String ACCEPTED = "accepted";
	public static final String DECLINED = "declined";

	public EventData() {
	}

	public EventData(String owner, String eventId, List<Contact> contacts) {
		super();
		this.owner = owner;
		this.eventId = eventId;
		this.contacts = contacts;
	}

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

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		return "EventData [owner=" + owner + ", eventId=" + eventId
				+ ", contacts=" + contacts + "]";
	}

	public void persist() throws IOException {
		DataConnection.addEvent(this);
	}

	public static void main(String[] args) throws IOException {

		EventData obj = new EventData("2", "1", new ArrayList<Contact>());
		obj.persist();
		System.out.println("done");

	}
}
