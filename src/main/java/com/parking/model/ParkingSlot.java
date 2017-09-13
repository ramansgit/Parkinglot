package com.parking.model;

/**
 * Carries Slot information in the system
 * @author ramans
 *
 */
public class ParkingSlot {

	@Override
	public String toString() {
		return "SlotsPojo [slotId=" + slotId + ", availabilty=" + availabilty + ", vehicle=" + vehicle + "]";
	}

	public boolean isAvailabilty() {
		return availabilty;
	}

	public void setAvailabilty(boolean availabilty) {
		this.availabilty = availabilty;
	}

	private int slotId;
	private boolean availabilty;
	private Vehicle vehicle;

	public int getSlotId() {
		return slotId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}
