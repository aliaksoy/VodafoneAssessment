package com.vodafone.aliaksoy.assessment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodafone.aliaksoy.assessment.model.Parking;
import com.vodafone.aliaksoy.assessment.model.ParkingDto;
import com.vodafone.aliaksoy.assessment.model.ResultModel;
import com.vodafone.aliaksoy.assessment.model.VehicleType;
import com.vodafone.aliaksoy.assessment.service.IGarageService;

/**
 * @author aaksoy
 *
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // need this in Spring Boot test
class VodafoneAssessmentApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IGarageService garageService;

	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	void testStatusPark() throws Exception {
		List<ParkingDto> parkingList = new ArrayList<>();
		ParkingDto parking = new ParkingDto();
		parking.setColor("red");
		parking.setPlate("34ABC28");
		parking.setVehicleType(VehicleType.CAR);

		List<Integer> parkingSlot = Arrays.asList(1, 2, 3, 4, 5);
		parking.setParkingSlot(parkingSlot);

		parkingList.add(parking);

		Mockito.when(garageService.statusPark()).thenReturn(parkingList);
		mockMvc.perform(get("/garage/status")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].color", Matchers.equalTo("red")))
				.andExpect(jsonPath("$[0].plate", Matchers.equalTo("34ABC28")))
				.andExpect(jsonPath("$[0].vehicleType", Matchers.equalTo("CAR")));
	}

	@Test
	void testIncludeParkWithCar() throws Exception {
		includePark(VehicleType.CAR);
	}

	@Test
	void testIncludeParkWithJeep() throws Exception {
		includePark(VehicleType.JEEP);

	}

	@Test
	void testIncludeParkWithTruck() throws Exception {
		includePark(VehicleType.TRUCK);

	}

	@Test
	void testLeavePark() throws Exception {
		ResultModel resultModel = new ResultModel("Leave parkingId: 1");
		Mockito.when(garageService.leavePark(1)).thenReturn(resultModel);

		String json = mapper.writeValueAsString(resultModel);
		mockMvc.perform(delete("/garage/leave/1").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.message", Matchers.equalTo("Leave parkingId: 1")));

	}

	private void includePark(VehicleType vehicleType) throws JsonProcessingException, Exception {
		Parking parking = new Parking();
		parking.setPlate("34543");
		parking.setColor("red");
		parking.setVehicleType(vehicleType);

		ResultModel resultModel = new ResultModel("Allocated " + vehicleType.getValue() + " lot.");
		Mockito.when(garageService.includePark(ArgumentMatchers.any())).thenReturn(resultModel);

		String json = mapper.writeValueAsString(parking);
		mockMvc.perform(post("/garage/include").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.message", Matchers.equalTo("Allocated " + vehicleType.getValue() + " lot.")));
	}

}
