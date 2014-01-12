package com.event;

import com.utility.UniqueIdGenerator;

public class EventIdGenerator extends UniqueIdGenerator{
	public static String generateUniqueEventId() {
		return UniqueIdGenerator.generateId();
	}
}
