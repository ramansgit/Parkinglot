package com.parking.ticket.manager;

import java.util.List;

import com.parking.exception.VehicleSearchException;
import com.parking.model.ParkingPojo;
import com.parking.model.TicketPojo;
import com.parking.model.VehiclePojo;

/**
 * Ticket Manager interface responsible for adding parking slots, issuing
 * parking ticket at entrance, collecting parking ticket at exit, and does
 * searching based on color and registration no
 * 
 * @author ramans
 *
 */
public interface ParkingManagerInterface {

	/**
	 * allows ticket manager to add parking slots
	 */
	public abstract void initailizeParkingSlots(int noOfSlots);

	/**
	 * allows ticket manager to issue parking ticket at entry
	 */
	public abstract TicketPojo issueParkingTiketAtEntrance(VehiclePojo vehicleß) throws Exception;

	/**
	 * allows ticket manager to collect parking ticket at exit
	 */
	public abstract boolean collectParkingTicketAtExit(TicketPojo returnTicket)throws Exception;

	/**
	 * allows ticket manager to find slot for given registrationNo
	 */
	public abstract int findSlotNoByRegistrationNo(String registerationNo)throws VehicleSearchException;

	/**
	 * allows ticket manager to find all registration no for given color
	 */
	public abstract List<String> findAllRegistrationNoByColor(String color)throws VehicleSearchException ;

	/**
	 * allows ticket manager to find all slot no for given color
	 */
	public abstract List<Integer> findAllSlotNoByColor(String color)throws VehicleSearchException ;

	
	public abstract ParkingPojo getParkingDetails();
	
	public abstract List<TicketPojo> getParkingStatus();
}
