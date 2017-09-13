package com.parking.model;

/**
 * Carries Ticket information in the system.
 * @author ramans
 *
 */
public class ParkingTicket {
	
	@Override
	public String toString() {
		return "TicketPojo [levelNo=" + levelNo + ", slotNo=" + slotNo + ", registerationNo=" + registerationNo
				+ ", colour=" + colour + "]";
	}
	public int getLevelNo() {
		return levelNo;
	}
	public void setLevelNo(int levelNo) {
		this.levelNo = levelNo;
	}

	private int levelNo;
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

}
