package com.parking.ticket.store;

import java.util.List;

import com.parking.model.ParkingPojo;
import com.parking.model.TicketPojo;
import com.parking.model.VehiclePojo;

public interface ParkingStoreInterface {

	/**
	 * stores parking slots info into memory.
	 */
	public abstract void addParkingSlots(ParkingPojo parking);

	/**
	 * store vehicle info and issue ticket to driver 
	 */
	public abstract TicketPojo getParkingSlot(VehiclePojo vehicle)throws Exception;
	
	

	/**
	 * collect parking ticket from vehicle drive and add to system
	 */
	public abstract void updateReturnTicketToStore(TicketPojo returnTicket);

	/**
	 * find slot for given registrationNo
	 */
	public abstract String findSlotNoByRegistrationNo(String registerationNo);

	/**
	 *  find all registration no for given color
	 */
	public abstract List<String> findAllRegistrationNoByColor(String color);

	/**
	 * find all slot no for given color
	 */
	public abstract List<Integer> findAllSlotNoByColor(String color);

	/**
	 * gets parked status
	 * @return
	 */
	public abstract List<TicketPojo> parkedStatus();
	
	
	
	
}
