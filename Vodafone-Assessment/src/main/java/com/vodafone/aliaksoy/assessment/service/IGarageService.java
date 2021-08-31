package com.vodafone.aliaksoy.assessment.service;

import java.util.List;
import com.vodafone.aliaksoy.assessment.model.Parking;
import com.vodafone.aliaksoy.assessment.model.ParkingDto;
import com.vodafone.aliaksoy.assessment.model.ResultModel;

/**
 * @author aaksoy
 *
 */

public interface IGarageService {

	public ResultModel includePark(Parking parking);

	public ResultModel leavePark(int parkingId);

	public List<ParkingDto> statusPark();

}
