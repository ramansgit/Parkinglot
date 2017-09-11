package com.parking.model;

import java.util.List;

public class ParkingPojo {
	
	@Override
	public String toString() {
		return "ParkingPojo [parkingId=" + parkingId + ", parkingName=" + parkingName + ", levels=" + levels + "]";
	}
	private int parkingId;
	private String parkingName;
	private List<LevelPojo> levels;

	public int getParkingId() {
		return parkingId;
	}
	public void setParkingId(int parkingId) {
		this.parkingId = parkingId;
	}
	public String getParkingName() {
		return parkingName;
	}
	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}
	public List<LevelPojo> getLevels() {
		return levels;
	}
	public void setLevels(List<LevelPojo> levels) {
		this.levels = levels;
	}
	
}
