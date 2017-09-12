package com.parking.model;

import java.util.List;

/**
 * Carries parking information in the system.
 * @author ramans
 *
 */
public class ParkingPojo {
	
	@Override
	public String toString() {
		return "ParkingPojo [ parkingName=" + parkingName + ", levels=" + levels + "]";
	}
	private String parkingName;
	private List<LevelPojo> levels;

	
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
