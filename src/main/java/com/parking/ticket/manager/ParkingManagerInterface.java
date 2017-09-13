package com.parking.ticket.manager;

import java.util.List;

import com.parking.exception.SearchException;
import com.parking.model.ParkingLot;
import com.parking.model.ParkingTicket;
import com.parking.model.Vehicle;

/**
 * parking Manager interface responsible for adding parking slots, issuing
 * parking parking at entrance, collecting parking parking at exit, and does
 * searching based on color and registration no
 * 
 * @author ramans
 *
 */
public interface ParkingManagerInterface {

	/**
	 * allows parking manager to add parking slots
	 */
	public abstract void setUpMultiStoreyParking(int noOfSlots);

	/**
	 * allows parking manager to issue parking parking at entry
	 */
	public  abstract  ParkingTicket issueParkingTiketAtEntrance(Vehicle vehicleß) throws Exception;

	/**
	 * allows parking manager to collect parking parking at exit
	 */
	public abstract boolean collectParkingTicketAtExit(ParkingTicket returnparking)throws Exception;

	/**
	 * allows parking manager to find slot for given registrationNo
	 */
	public abstract int findSlotNoByRegistrationNo(String registerationNo)throws SearchException;

	/**
	 * allows parking manager to find all registration no for given color
	 */
	public abstract List<String> findAllRegistrationNoByColor(String color)throws SearchException ;

	/**
	 * allows parking manager to find all slot no for given color
	 */
	public abstract List<Integer> findAllSlotNoByColor(String color)throws SearchException ;

	
	/**
	 * allows parking manager to view parking object details
	 * @return
	 */
	public abstract ParkingLot getParkingDetails();
	
	/**
	 * views the parking status
	 * @return
	 */
	public abstract List<ParkingTicket> getParkingStatus();
	
	public abstract ParkingTicket getTicketInfoBySlot(int slotId);
	
	
}
