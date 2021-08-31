package com.vodafone.aliaksoy.assessment.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.vodafone.aliaksoy.assessment.exception.GarageParkingException;
import com.vodafone.aliaksoy.assessment.model.Parking;
import com.vodafone.aliaksoy.assessment.model.ParkingDto;
import com.vodafone.aliaksoy.assessment.model.StaticObject;
import com.vodafone.aliaksoy.assessment.model.VehicleType;

/**
 * @author aaksoy
 *
 */

public class GarageUtil {

	private GarageUtil() {
		throw new IllegalStateException("Constructor was not called");
	}

	public static int[] getAvailableSlots(Parking parking) {

		int[] result = null;
		if (parking.getVehicleType() == VehicleType.CAR) {
			if (StaticObject.availableSlots.length >= VehicleType.CAR.getValue())
				result = new int[] { StaticObject.availableSlots[0] };
			else
				throw new GarageParkingException(Constant.GARAGE_IS_FULL_MESSAGE);

		} else if (parking.getVehicleType() == VehicleType.JEEP) {
			if (StaticObject.availableSlots.length >= VehicleType.JEEP.getValue())
				result = new int[] { StaticObject.availableSlots[0], StaticObject.availableSlots[1] };
			else
				throw new GarageParkingException(Constant.GARAGE_IS_FULL_MESSAGE);
		} else if (parking.getVehicleType() == VehicleType.TRUCK) {
			if (StaticObject.availableSlots.length >= VehicleType.TRUCK.getValue())
				result = new int[] { StaticObject.availableSlots[0], StaticObject.availableSlots[1],
						StaticObject.availableSlots[2], StaticObject.availableSlots[3] };
			else
				throw new GarageParkingException(Constant.GARAGE_IS_FULL_MESSAGE);
		}

		return result;

	}

	public static void updateAvailableSlotList(ParkingDto parkingDto) {

		List<Integer> slotList = parkingDto.getParkingSlot();
		List<Integer> availableSlotList = Arrays.stream(StaticObject.availableSlots).boxed()
				.collect(Collectors.toList());
		availableSlotList.removeAll(slotList);
		StaticObject.availableSlots = availableSlotList.stream().mapToInt(i -> i).toArray();
		updateParkingData(parkingDto);

	}

	public static void updateParkingData(ParkingDto parkingDto) {
		if (StaticObject.parkingDataMap.size() > 0)
			StaticObject.parkingDataMap.put(StaticObject.parkingDataMap.size() + 1, parkingDto);
		else// first parking
			StaticObject.parkingDataMap.put(1, parkingDto);
	}

	public static void removeParking(int parkingId, List<Integer> parkingSlots) {

		List<Integer> availableSlotList = Arrays.stream(StaticObject.availableSlots).boxed()
				.collect(Collectors.toList());
		List<Integer> newList = Stream.concat(parkingSlots.stream(), availableSlotList.stream())
				.collect(Collectors.toList());
		StaticObject.availableSlots = newList.stream().mapToInt(i -> i).toArray();
		StaticObject.parkingDataMap.remove(parkingId);

	}
}
