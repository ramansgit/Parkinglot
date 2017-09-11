package com.parking.model;

import java.util.List;

public class LevelPojo {
	
	@Override
	public String toString() {
		return "LevelPojo [levelNo=" + levelNo + ", slots=" + slots + "]";
	}
	private int levelNo;
	private List<SlotsPojo> slots;

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
