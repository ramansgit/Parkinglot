package com.parking.ticket.manager;

import java.util.ArrayList;
import java.util.List;

import com.parking.model.LevelPojo;
import com.parking.model.ParkingPojo;
import com.parking.model.SlotsPojo;
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

	private static ParkingManagerService ticketManagerInstance;

	/**
	 * follows singleton pattern, uses double checking approach to ensure thread
	 * safe.
	 * 
	 * @return
	 */
	public static ParkingManagerService getTicketManagerInstance() {
		if (ticketManagerInstance == null) {
			// synchronized (ticketManagerInstance) {
			ticketManagerInstance = new ParkingManagerService();
			// }
		}
		return ticketManagerInstance;
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
		ParkingPojo parkingObj = new ParkingPojo();

		if (noOfSlots > 0) {
			parkingObj.setParkingName("MultiStoreParkingMall");
			int max_slots_per_level = 5;
			if (noOfSlots <= max_slots_per_level) {
				LevelPojo level = new LevelPojo();
				List<LevelPojo> levels = new ArrayList<LevelPojo>();
				List<SlotsPojo> slots = new ArrayList<SlotsPojo>();
				level.setLevelNo(0);
				for (int slotIndex = 1; slotIndex <= noOfSlots; slotIndex++) {
					SlotsPojo slot = new SlotsPojo();
					slot.setSlotId(slotIndex);
					slot.setAvailabilty(true);
					slots.add(slot);

				}
				level.setSlots(slots);
				level.setAvailableSlotCount(noOfSlots);
				levels.add(level);
				parkingObj.setLevels(levels);
			} else {
				int noOfLevels = noOfSlots / max_slots_per_level;
				System.out.println(noOfLevels);
				List<LevelPojo> levels = new ArrayList<LevelPojo>();
				int slotIndex = 1;

				for (int levelIndex = 0; levelIndex <= noOfLevels; levelIndex++) {
					int limit = 0;
					if (noOfSlots > slotIndex) {
						limit = noOfSlots - slotIndex;
					} else {
						limit = slotIndex - noOfSlots;
					}
					int boundry = 0;
					if (limit > max_slots_per_level) {
						boundry = max_slots_per_level;
					} else {
						boundry = limit + 1;
					}
					LevelPojo level = new LevelPojo();

					List<SlotsPojo> slots = new ArrayList<SlotsPojo>();
					level.setLevelNo(levelIndex);
					level.setAvailableSlotCount(boundry);
					while (boundry >= 1) {
						boundry--;
						SlotsPojo slot = new SlotsPojo();
						slot.setSlotId(slotIndex);
						slot.setAvailabilty(true);
						slots.add(slot);
						slotIndex++;
					}

					level.setSlots(slots);
					levels.add(level);
				}

				parkingObj.setLevels(levels);
			}

			System.out.println("Parking Obj" + parkingObj);

			ParkingStore.getTicketStoreInstance().addParkingSlots(parkingObj);

		}

	}

	/**
	 * issues parking ticket when vehicle enters the parking entrance
	 * 
	 * @throws Exception
	 */
	public TicketPojo issueParkingTiketAtEntrance(VehiclePojo vehicle) throws Exception {
		return ParkingStore.getTicketStoreInstance().getParkingSlot(vehicle);

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

	public ParkingPojo getParkingObj() {
		return ParkingStore.getTicketStoreInstance().getParkingObj();
	}
}
