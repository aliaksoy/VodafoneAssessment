package com.vodafone.aliaksoy.assessment.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.vodafone.aliaksoy.assessment.exception.GarageParkingException;
import com.vodafone.aliaksoy.assessment.model.Parking;
import com.vodafone.aliaksoy.assessment.model.ParkingDto;
import com.vodafone.aliaksoy.assessment.model.ResultModel;
import com.vodafone.aliaksoy.assessment.model.StaticObject;
import com.vodafone.aliaksoy.assessment.util.Constant;
import com.vodafone.aliaksoy.assessment.util.GarageUtil;

/**
 * @author aaksoy
 *
 */

@Service
public class GarageServiceImpl implements IGarageService {

	@Override
	public ResultModel includePark(Parking parking) throws GarageParkingException {

		int[] slots = GarageUtil.getAvailableSlots(parking);
		ParkingDto dto = new ParkingDto();
		dto.setColor(parking.getColor());
		dto.setPlate(parking.getPlate());
		dto.setVehicleType(parking.getVehicleType());
		dto.setParkingSlot(Arrays.stream(slots).boxed().collect(Collectors.toList()));

		GarageUtil.updateAvailableSlotList(dto);
		return new ResultModel("Allocated " + Arrays.toString(slots) + " lot.");
	}

	@Override
	public ResultModel leavePark(int parkingId) {
		List<Integer> parkingSlots = StaticObject.parkingDataMap.get(parkingId).getParkingSlot();
		if (parkingSlots == null)
			throw new GarageParkingException(Constant.INVALID_PARKING_ID_MESSAGE);

		GarageUtil.removeParking(parkingId, parkingSlots);
		return new ResultModel("Leave parkingId: " + parkingId);

	}

	@Override
	public List<ParkingDto> statusPark() {
		List<ParkingDto> result = new ArrayList<>();
		StaticObject.parkingDataMap.entrySet().stream().forEach(e -> result.add(e.getValue()));
		return result;
	}

}
