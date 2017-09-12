package com.parking.ticket.store;

import java.util.List;

import com.parking.exception.ParkingSlotException;
import com.parking.exception.SearchException;
import com.parking.model.ParkingPojo;
import com.parking.model.TicketPojo;
import com.parking.model.VehiclePojo;

public interface ParkingStoreInterface {

	/**
	 * stores parking slots info into memory.
	 */
	public abstract void setUpMultiStoreyParking(int noOfSlots);

	/**
	 * store vehicle info and issue ticket to driver
	 */
	public abstract TicketPojo getParkingSlot(VehiclePojo vehicle) throws ParkingSlotException, Exception;

	/**
	 * collect parking ticket from vehicle drive and add to system
	 */
	public abstract boolean updateReturnTicketToStore(TicketPojo returnTicket) throws ParkingSlotException, Exception;

	/**
	 * find slot for given registrationNo
	 */
	public abstract int findSlotNoByRegistrationNo(String registerationNo) throws SearchException;

	/**
	 * find all registration no for given color
	 */
	public abstract List<String> findAllRegistrationNoByColor(String color) throws SearchException;

	/**
	 * find all slot no for given color
	 */
	public abstract List<Integer> findAllSlotNoByColor(String color) throws SearchException;

	/**
	 * gets parking status
	 * 
	 * @return
	 */
	public abstract List<TicketPojo> getParkingStatus();

	/**
	 * get parking details
	 * 
	 * @return
	 */
	public abstract ParkingPojo getParkingDetails();

}
