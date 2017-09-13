package com.parking.model;

import java.util.List;

/**
 * Level pojo carries no of slots in the level and gives a count of available count.
 * @author ramans
 *
 */
public class ParkingLevel {
	
	public int getAvailableSlotCount() {
		return availableSlotCount;
	}
	public void setAvailableSlotCount(int availableSlotCount) {
		this.availableSlotCount = availableSlotCount;
	}
	
	public void increaseSlotCount(){
		availableSlotCount++;
	}
	
	public void decreaseSlotCount(){
		availableSlotCount--;
	}
	
	@Override
	public String toString() {
		return "LevelPojo [levelNo=" + levelNo + ", slots=" + slots + ", availableSlotCount=" + availableSlotCount + "]";
	}
	
	
	private int levelNo;
	private List<ParkingSlot> slots;
	
	private int availableSlotCount;

	public int getLevelNo() {
		return levelNo;
	}
	public void setLevelNo(int levelNo) {
		this.levelNo = levelNo;
	}
	public List<ParkingSlot> getSlots() {
		return slots;
	}
	public void setSlots(List<ParkingSlot> slots) {
		this.slots = slots;
	}
	
}
