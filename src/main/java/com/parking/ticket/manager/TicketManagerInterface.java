package com.parking.ticket.manager;

import java.util.List;

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
public interface TicketManagerInterface {

	/**
	 * allows ticket manager to add parking slots
	 */
	public abstract void addParkingSlots(ParkingPojo parking);

	/**
	 * allows ticket manager to issue parking ticket at entry
	 */
	public abstract TicketPojo issueParkingTiketAtEntrance(VehiclePojo vehicleß);

	/**
	 * allows ticket manager to collect parking ticket at exit
	 */
	public abstract void collectParkingTicketAtExit(TicketPojo returnTicket);

	/**
	 * allows ticket manager to find slot for given registrationNo
	 */
	public abstract String findSlotNoByRegistrationNo(String registerationNo);

	/**
	 * allows ticket manager to find all registration no for given color
	 */
	public abstract List<String> findAllRegistrationNoByColor(String color);

	/**
	 * allows ticket manager to find all slot no for given color
	 */
	public abstract List<Integer> findAllSlotNoByColor(String color);

}
