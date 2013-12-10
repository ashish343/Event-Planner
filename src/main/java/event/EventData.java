package event;

import java.util.List;

import contacts.Contact;

public class EventData {
	private String owner;
	private String eventId;
	private List<Contact> contacts;

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

}
