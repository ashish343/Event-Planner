package event;

import utility.UniqueIdGenerator;

public class EventIdGenerator extends UniqueIdGenerator{
	public static String generateUniqueEventId() {
		return UniqueIdGenerator.generateId();
	}
}
