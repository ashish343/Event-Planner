package event;

import java.util.ArrayList;

import contacts.Contact;

public class EventData {
	private String owner;
	private String eventId;
	private ArrayList<Contact> contacts;

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

	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		return "EventData [owner=" + owner + ", eventId=" + eventId
				+ ", contacts=" + contacts + "]";
	}

}
