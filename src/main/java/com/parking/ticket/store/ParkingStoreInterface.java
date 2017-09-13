package com.parking.ticket.store;

import java.util.List;

import com.parking.exception.ParkingSlotException;
import com.parking.exception.SearchException;
import com.parking.model.ParkingLot;
import com.parking.model.ParkingTicket;
import com.parking.model.Vehicle;

public interface ParkingStoreInterface {

	/**
	 * stores parking slots info into memory.
	 */
	public abstract void setUpMultiStoreyParking(int noOfSlots);

	/**
	 * store vehicle info and issue ticket to driver
	 */
	public abstract ParkingTicket getParkingSlot(Vehicle vehicle) throws ParkingSlotException, Exception;

	/**
	 * collect parking ticket from vehicle drive and add to system
	 */
	public abstract boolean updateReturnTicketToStore(ParkingTicket returnTicket) throws ParkingSlotException, Exception;

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
	public abstract List<ParkingTicket> getParkingStatus();

	/**
	 * get parking details
	 * 
	 * @return
	 */
	public abstract ParkingLot getParkingDetails();

}
