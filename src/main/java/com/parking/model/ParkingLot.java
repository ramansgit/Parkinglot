package com.parking.model;

import java.util.List;

/**
 * Carries parking information in the system.
 * @author ramans
 *
 */
public class ParkingLot {
	
	@Override
	public String toString() {
		return "ParkingPojo [ parkingName=" + parkingName + ", levels=" + levels + "]";
	}
	private String parkingName;
	private List<ParkingLevel> levels;

	
	public String getParkingName() {
		return parkingName;
	}
	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}
	public List<ParkingLevel> getLevels() {
		return levels;
	}
	public void setLevels(List<ParkingLevel> levels) {
		this.levels = levels;
	}
	
}
