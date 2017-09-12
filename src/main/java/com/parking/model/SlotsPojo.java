package com.parking.model;

/**
 * Carries Slot information in the system
 * @author ramans
 *
 */
public class SlotsPojo {

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
	private VehiclePojo vehicle;

	public int getSlotId() {
		return slotId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}

	public VehiclePojo getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehiclePojo vehicle) {
		this.vehicle = vehicle;
	}
}
