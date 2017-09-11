package com.parking.ticket.manager;

import java.util.List;

import com.parking.model.ParkingPojo;
import com.parking.model.TicketPojo;
import com.parking.model.VehiclePojo;

/**
 * Ticket Manager service responsible for adding parking slots, issuing
 * parking ticket at entrance, collecting parking ticket at exit, and does
 * searching based on color and registration no
 * 
 * @author ramans
 *
 */
public class TiketManagerService implements TicketManagerInterface {

	private static TiketManagerService ticketManagerInstance;

	
	/**
	 * follows singleton pattern, uses double checking approach to ensure thread
	 * safe.
	 * 
	 * @return
	 */
	public static TiketManagerService getTicketManagerInstance() {
		if (ticketManagerInstance == null) {
			synchronized (ticketManagerInstance) {
				ticketManagerInstance = new TiketManagerService();
			}
		}
		return ticketManagerInstance;
	}

	
	/**
	 * not allowed to create instance of TiketManagerService via constructor.
	 */
	private TiketManagerService() {

	}

	/**
	 * allows ticket manager to add parking slot in the system
	 */
	public void addParkingSlots(ParkingPojo parking) {

	}

	/**
	 * issues parking ticket when vehicle enters the parking entrance
	 */
	public TicketPojo issueParkingTiketAtEntrance(VehiclePojo vehicleß) {

		return null;
	}

	/**
	 * collects parking ticket when vehicle leaves the parking area
	 */

	public void collectParkingTicketAtExit(TicketPojo returnTicket) {

	}

	/**
	 * find slot no by registration no
	 */
	public String findSlotNoByRegistrationNo(String registerationNo) {

		return null;
	}

	/**
	 * find all registration no by color
	 */
	public List<String> findAllRegistrationNoByColor(String color) {

		return null;
	}

	/**
	 * find all slot no by color
	 */
	public List<Integer> findAllSlotNoByColor(String color) {

		return null;
	}

}
