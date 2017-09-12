package com.parking.model;

import java.util.List;

public class LevelPojo {
	
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
	private List<SlotsPojo> slots;
	
	private int availableSlotCount;

	public int getLevelNo() {
		return levelNo;
	}
	public void setLevelNo(int levelNo) {
		this.levelNo = levelNo;
	}
	public List<SlotsPojo> getSlots() {
		return slots;
	}
	public void setSlots(List<SlotsPojo> slots) {
		this.slots = slots;
	}
	
}
