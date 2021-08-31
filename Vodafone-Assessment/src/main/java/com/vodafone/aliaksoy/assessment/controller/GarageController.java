package com.vodafone.aliaksoy.assessment.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vodafone.aliaksoy.assessment.exception.GarageParkingException;
import com.vodafone.aliaksoy.assessment.model.Parking;
import com.vodafone.aliaksoy.assessment.model.ParkingDto;
import com.vodafone.aliaksoy.assessment.model.ResultModel;
import com.vodafone.aliaksoy.assessment.service.IGarageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author aaksoy
 *
 */

@RestController
@RequestMapping("/garage")
@Api(value = "User Rest Service")
public class GarageController {

	@Autowired
	private IGarageService garageService;

	@ApiOperation(value = "Include Park")
	@PostMapping(path = "/include", produces = "application/json")
	public ResponseEntity<ResultModel> includePark(@RequestBody Parking parking) {
		ResultModel result = null;
		try {
			result = garageService.includePark(parking);
		} catch (GarageParkingException e) {
			return ResponseEntity.ok(new ResultModel(e.getMessage()));
		}
		return ResponseEntity.ok(result);
	}

	@ApiOperation(value = "Leave Park")
	@DeleteMapping(path = "/leave/{parkingId}", produces = "application/json")
	public ResponseEntity<ResultModel> leavePark(@PathVariable("parkingId") int parkingId) {
		ResultModel result = null;
		try {
			result = garageService.leavePark(parkingId);
		} catch (GarageParkingException e) {
			return ResponseEntity.ok(new ResultModel(e.getMessage()));
		}
		return ResponseEntity.ok(result);
	}

	@ApiOperation(value = "Get Status Park")
	@GetMapping(path = "/status", produces = "application/json")
	public ResponseEntity<List<ParkingDto>> statusPark() {
		List<ParkingDto> result = garageService.statusPark();
		return ResponseEntity.ok(result);
	}

}
