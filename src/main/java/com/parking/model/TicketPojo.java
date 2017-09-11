package com.parking.model;

public class TicketPojo {
	
	
	private int slotNo;
	private String registerationNo;
	private String colour;

	public int getSlotNo() {
		return slotNo;
	}
	public void setSlotNo(int slotNo) {
		this.slotNo = slotNo;
	}
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
	
	@Override
	public String toString() {
		return "TicketPojo [slotNo=" + slotNo + ", registerationNo=" + registerationNo + ", colour=" + colour + "]";
	}
}
