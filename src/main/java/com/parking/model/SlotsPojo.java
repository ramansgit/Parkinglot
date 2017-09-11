package com.parking.model;

public class SlotsPojo {
	

	@Override
	public String toString() {
		return "SlotsPojo [slotId=" + slotId + ", vehicle=" + vehicle + "]";
	}
	private int slotId;
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
