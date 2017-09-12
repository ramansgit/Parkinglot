package com.parking.ticket.manager;

import java.util.List;

import com.parking.exception.SearchException;
import com.parking.model.ParkingPojo;
import com.parking.model.TicketPojo;
import com.parking.model.VehiclePojo;
import com.parking.ticket.store.ParkingStore;

/**
 * ParkingManagerService service responsible for adding parking slots, issuing
 * parking ticket at entrance, collecting parking ticket at exit, and does
 * searching based on color and registration no
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
			synchronized (ParkingManagerService.class) {
				if (parkingManagerInstance == null) {
					parkingManagerInstance = new ParkingManagerService();
				}
			}
		}
		return parkingManagerInstance;
	}

	/**
	 * not allowed to create instance of TiketManagerService via constructor.
	 */
	private ParkingManagerService() {

	}

	/**
	 * allows ticket manager to add parking slot in the system,making it thread
	 * safe to handle concurrency
	 */
	public synchronized void setUpMultiStoreyParking(int noOfSlots) {
		ParkingStore.getParkingStoreInstance().setUpMultiStoreyParking(noOfSlots);
	}

	/**
	 * issues parking ticket when vehicle enters the parking entrance,making it
	 * thread safe to handle concurrency
	 * 
	 * @throws Exception
	 */
	public synchronized TicketPojo issueParkingTiketAtEntrance(VehiclePojo vehicle) throws Exception {
		return ParkingStore.getParkingStoreInstance().getParkingSlot(vehicle);

	}

	/**
	 * collects parking ticket when vehicle leaves the parking area,making it
	 * thread safe to handle concurrency
	 * 
	 * @throws Exception
	 */
	public synchronized boolean collectParkingTicketAtExit(TicketPojo returnTicket) throws Exception {
		return ParkingStore.getParkingStoreInstance().updateReturnTicketToStore(returnTicket);
	}

	/**
	 * find slot no by registration no
	 */
	public int findSlotNoByRegistrationNo(String registrationNo) throws SearchException {

		return ParkingStore.getParkingStoreInstance().findSlotNoByRegistrationNo(registrationNo);
	}

	/**
	 * find all registration no by color
	 */
	public List<String> findAllRegistrationNoByColor(String color) throws SearchException {

		return ParkingStore.getParkingStoreInstance().findAllRegistrationNoByColor(color);
	}

	/**
	 * find all slot no by color
	 */
	public List<Integer> findAllSlotNoByColor(String color) throws SearchException {

		return ParkingStore.getParkingStoreInstance().findAllSlotNoByColor(color);
	}

	/**
	 * get parking details from store
	 */
	public ParkingPojo getParkingDetails() {
		return ParkingStore.getParkingStoreInstance().getParkingDetails();
	}

	/**
	 * get parking status from store
	 */
	public List<TicketPojo> getParkingStatus() {

		return ParkingStore.getParkingStoreInstance().getParkingStatus();
	}

}
