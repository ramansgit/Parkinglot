package com.parking.ticket.manager;

import java.util.List;

import com.parking.exception.VehicleSearchException;
import com.parking.model.ParkingPojo;
import com.parking.model.TicketPojo;
import com.parking.model.VehiclePojo;
import com.parking.ticket.store.ParkingStore;

/**
 * Ticket Manager service responsible for adding parking slots, issuing parking
 * ticket at entrance, collecting parking ticket at exit, and does searching
 * based on color and registration no
 * 
 * @author ramans
 *
 */
public class ParkingManagerService implements ParkingManagerInterface {

	private static ParkingManagerService parkingManagerInstance;

	/**
	 * follows singleton pattern, uses double checking approach to ensure thread
	 * safe.
	 * 
	 * @return
	 */
	public static ParkingManagerService getParkingManager() {
		if (parkingManagerInstance == null) {
			parkingManagerInstance = new ParkingManagerService();
		}
		return parkingManagerInstance;
	}

	/**
	 * not allowed to create instance of TiketManagerService via constructor.
	 */
	private ParkingManagerService() {

	}

	/**
	 * allows ticket manager to add parking slot in the system
	 */
	public void initailizeParkingSlots(int noOfSlots) {
		ParkingStore.getParkingStoreInstance().initalizeParkingSlots(noOfSlots);
	}

	/**
	 * issues parking ticket when vehicle enters the parking entrance
	 * 
	 * @throws Exception
	 */
	public TicketPojo issueParkingTiketAtEntrance(VehiclePojo vehicle) throws Exception {
		return ParkingStore.getParkingStoreInstance().getParkingSlot(vehicle);

	}

	/**
	 * collects parking ticket when vehicle leaves the parking area
	 * 
	 * @throws Exception
	 */
	public boolean collectParkingTicketAtExit(TicketPojo returnTicket) throws Exception {
		return ParkingStore.getParkingStoreInstance().updateReturnTicketToStore(returnTicket);
	}

	/**
	 * find slot no by registration no
	 */
	public int findSlotNoByRegistrationNo(String registrationNo) throws VehicleSearchException {

		return ParkingStore.getParkingStoreInstance().findSlotNoByRegistrationNo(registrationNo);
	}

	/**
	 * find all registration no by color
	 */
	public List<String> findAllRegistrationNoByColor(String color) throws VehicleSearchException {

		return ParkingStore.getParkingStoreInstance().findAllRegistrationNoByColor(color);
	}

	/**
	 * find all slot no by color
	 */
	public List<Integer> findAllSlotNoByColor(String color) throws VehicleSearchException {

		return ParkingStore.getParkingStoreInstance().findAllSlotNoByColor(color);
	}

	public ParkingPojo getParkingDetails() {
		return ParkingStore.getParkingStoreInstance().getParkingDetails();
	}

	public List<TicketPojo> getParkingStatus() {

		return ParkingStore.getParkingStoreInstance().getParkingStatus();
	}
}
