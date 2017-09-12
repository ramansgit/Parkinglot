package com.parking.model;

public class VehiclePojo {

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
