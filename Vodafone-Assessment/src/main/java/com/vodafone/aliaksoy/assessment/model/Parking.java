package com.vodafone.aliaksoy.assessment.model;

import io.swagger.annotations.ApiModel;

/**
 * @author aaksoy
 *
 */

@ApiModel(value = "Parking", description = "Parking Model")
public class Parking {

	private String plate;
	private String color;
	private VehicleType vehicleType;

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Override
	public String toString() {
		return "Parking [plate=" + plate + ", color=" + color + ", vehicleType=" + vehicleType + "]";
	}

}
