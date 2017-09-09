package com.parking.ticket.manager;

public interface TicketManagerInterface {

	/**
	 * allows ticket manager to add slots
	 */
	public abstract void addSlots();
	
	/**
	 * allows ticket manager to issue ticket at entry
	 */
	public abstract void issueTiketAtEntry();
	
	/**
	 * allows ticket manager to collect  tickets at exitß
	 */
	public abstract void collectTikcetAtExit();
	
	
	/**
	 * allows ticket manager to find slots for given color
	 */
	public abstract void findSlotsByColor();
	
	
	/**
	 * allows ticket manager to find plateno for given color
	 */
	public abstract void findPlateNoByColor();
	
}
