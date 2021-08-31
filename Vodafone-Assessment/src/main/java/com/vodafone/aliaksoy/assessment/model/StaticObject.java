package com.vodafone.aliaksoy.assessment.model;

import java.util.HashMap;
import java.util.Map;

public class StaticObject {

	private StaticObject() {
		throw new IllegalStateException("Constructor was not called");
	}

	public static int[] availableSlots = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	public static Map<Integer, ParkingDto> parkingDataMap = new HashMap<>();
}
