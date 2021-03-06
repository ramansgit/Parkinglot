package com.parking.model;

/**
 * Carries Vehicle information in the system.
 * @author ramans
 *
 */
public class Vehicle {

	@Override
	public String toString() {
		return "VehiclePojo [registrationNo=" + registrationNo + ", colour=" + colour + "]";
	}

	private String registrationNo;
	private String colour;

	public String getRegisterationNo() {
		return registrationNo;
	}

	public void setRegisterationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

}
