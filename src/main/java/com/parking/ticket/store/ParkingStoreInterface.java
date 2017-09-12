package com.parking.ticket.store;

import java.util.List;

import com.parking.exception.VehicleSearchException;
import com.parking.model.ParkingPojo;
import com.parking.model.TicketPojo;
import com.parking.model.VehiclePojo;

public interface ParkingStoreInterface {

	/**
	 * stores parking slots info into memory.
	 */
	public abstract void initalizeParkingSlots(int noOfSlots);

	/**
	 * store vehicle info and issue ticket to driver
	 */
	public abstract TicketPojo getParkingSlot(VehiclePojo vehicle) throws Exception;

	/**
	 * collect parking ticket from vehicle drive and add to system
	 */
	public abstract boolean updateReturnTicketToStore(TicketPojo returnTicket) throws Exception;

	/**
	 * find slot for given registrationNo
	 */
	public abstract int findSlotNoByRegistrationNo(String registerationNo) throws VehicleSearchException;

	/**
	 * find all registration no for given color
	 */
	public abstract List<String> findAllRegistrationNoByColor(String color) throws VehicleSearchException;

	/**
	 * find all slot no for given color
	 */
	public abstract List<Integer> findAllSlotNoByColor(String color) throws VehicleSearchException;

	/**
	 * gets parked status
	 * 
	 * @return
	 */
	public abstract List<TicketPojo> getParkingStatus();

	public abstract ParkingPojo getParkingDetails();

}
