package com.parking.ticket.manager;

import java.util.List;

import com.parking.exception.SearchException;
import com.parking.model.ParkingLot;
import com.parking.model.ParkingTicket;
import com.parking.model.Vehicle;
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
	 * allows ticket manager to add parking slot in the system
	 */
	public  void setUpMultiStoreyParking(int noOfSlots) {
		ParkingStore.getParkingStoreInstance().setUpMultiStoreyParking(noOfSlots);
	}

	/**
	 * issues parking ticket when vehicle enters the parking entrance
	 * 
	 * @throws Exception
	 */
	public  ParkingTicket issueParkingTiketAtEntrance(Vehicle vehicle) throws Exception {
		return ParkingStore.getParkingStoreInstance().getParkingSlot(vehicle);

	}

	/**
	 * collects parking ticket when vehicle leaves the parking area,
	 * 
	 * @throws Exception
	 */
	public  boolean collectParkingTicketAtExit(ParkingTicket returnTicket) throws Exception {
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
	public ParkingLot getParkingDetails() {
		return ParkingStore.getParkingStoreInstance().getParkingDetails();
	}

	/**
	 * get parking status from store
	 */
	public List<ParkingTicket> getParkingStatus() {

		return ParkingStore.getParkingStoreInstance().getParkingStatus();
	}

	/**
	 * get ticket info by slot id.
	 */
	public ParkingTicket getTicketInfoBySlot(int slotId) {
		return ParkingStore.getParkingStoreInstance().getTicketInfoBySlot(slotId);
	}

}
