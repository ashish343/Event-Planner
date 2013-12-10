package event;

public enum EventParams {
	OBJECT_ID("ObjectId"), CONTACT_LIST("contacts"), NAME("name"), CONTACT_NUMBER(
			"contactNumber");

	private String eventParams;

	private EventParams(String s) {
		eventParams = s;
	}

	public String toString() {
		return eventParams;
	}
}
