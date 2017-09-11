package com.parking.model;

public class VehiclePojo {
	
	
	
	@Override
	public String toString() {
		return "VehiclePojo [registerationNo=" + registerationNo + ", colour=" + colour + ", driveName=" + driveName
				+ ", mobileNo=" + mobileNo + "]";
	}
	
	private String registerationNo;
	private String colour;
	private String driveName;
	private String mobileNo;
	
	
	public String getRegisterationNo() {
		return registerationNo;
	}
	public void setRegisterationNo(String registerationNo) {
		this.registerationNo = registerationNo;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getDriveName() {
		return driveName;
	}
	public void setDriveName(String driveName) {
		this.driveName = driveName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	
}
