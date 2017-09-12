package com.parking.ticket.store;

import java.util.List;

import com.parking.exception.ParkinSlotFullException;
import com.parking.model.LevelPojo;
import com.parking.model.ParkingPojo;
import com.parking.model.SlotsPojo;
import com.parking.model.TicketPojo;
import com.parking.model.VehiclePojo;

/**
 * TicketStore acts as in memory store for managing tickets in the system. This
 * store can be replaced with dao layer if required.
 * 
 * @author ramans
 *
 */
public class ParkingStore implements ParkingStoreInterface {

	public ParkingPojo getParkingObj() {
		return parkingObj;
	}

	/**
	 * global instance of TicketStore class , follows singleton design pattern.
	 */
	private static ParkingStore ticketStoreInstance = null;

	private static ParkingPojo parkingObj = null;

	/**
	 * follows singleton pattern, uses double checking approach to ensure thread
	 * safe.
	 * 
	 * @return
	 */
	public static ParkingStore getTicketStoreInstance() {
		if (ticketStoreInstance == null) {
			// synchronized (ticketStoreInstance) {
			// if(ticketStoreInstance == null){
			ticketStoreInstance = new ParkingStore();
			// }
			// }
		}
		return ticketStoreInstance;
	}

	private ParkingStore() {

	}

	public TicketPojo getParkingSlot(VehiclePojo vehicle) throws Exception {
		
		TicketPojo ticketPojo = new TicketPojo();
		
		int slotNo = 0;
		if (vehicle == null) {
			throw new Exception("Invalid vehicle");
		}
		ParkingPojo parking = getParkingObj();
		if (parking == null) {
			throw new Exception("Invalid Parking Object");
		}
		List<LevelPojo> levels = parking.getLevels();
		if (levels == null) {
			throw new Exception("Invalid Levels Object");
		}
		for (LevelPojo level : levels) {
			if (level.getAvailableSlotCount() > 0) {
				List<SlotsPojo> slots = level.getSlots();
				if (slots == null) {
					continue;
				}
				for (SlotsPojo slot : slots) {
					if (slot.isAvailabilty()) {
						slot.setAvailabilty(false);
						slot.setVehicle(vehicle);
						slotNo = slot.getSlotId();
						level.decreaseSlotCount();
						ticketPojo.setColour(vehicle.getColour());
						ticketPojo.setRegisterationNo(vehicle.getRegisterationNo());
						ticketPojo.setSlotNo(slotNo);
						ticketPojo.setLevelNo(level.getLevelNo());
						return ticketPojo;
					}
				}

			}

		}
		throw new ParkinSlotFullException("PARKING_FULL_EXCEPTION", "Parking slots not available");
	}

	public void updateReturnTicketToStore(TicketPojo returnTicket) {

	}

	public String findSlotNoByRegistrationNo(String registerationNo) {
		return null;
	}

	public List<String> findAllRegistrationNoByColor(String color) {
		return null;
	}

	public List<Integer> findAllSlotNoByColor(String color) {
		return null;
	}

	public List<TicketPojo> parkedStatus() {

		return null;
	}

	public void addParkingSlots(ParkingPojo parking) {
		parkingObj = parking;

	}

}
