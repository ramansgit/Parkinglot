package com.parking.ticket.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.parking.exception.ParkinSlotFullException;
import com.parking.exception.VehicleSearchException;
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

	/**
	 * global instance of TicketStore class , follows singleton design pattern.
	 */
	private static ParkingStore parkingStoreInstance = null;

	private static ParkingPojo parkingObj = new ParkingPojo();

	private static Map<Integer, TicketPojo> issuedTickets = new HashMap<Integer, TicketPojo>();
	private static Map<String, TicketPojo> vehicleCache = new HashMap<String, TicketPojo>();
	private static Map<String, Map<String, TicketPojo>> colorCache = new HashMap<String, Map<String, TicketPojo>>();

	/**
	 * follows singleton pattern, uses double checking approach to ensure thread
	 * safe.
	 * 
	 * @return
	 */
	public static ParkingStore getParkingStoreInstance() {
		if (parkingStoreInstance == null) {
			parkingStoreInstance = new ParkingStore();
		}
		return parkingStoreInstance;
	}

	private ParkingStore() {

	}

	public TicketPojo getParkingSlot(VehiclePojo vehicle) throws Exception {

		TicketPojo ticketPojo = new TicketPojo();

		int slotNo = 0;
		if (vehicle == null) {
			throw new Exception("Invalid vehicle");
		}
		ParkingPojo parking = getParkingDetails();
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

						addToIssuedTickets(ticketPojo);
						addToVehicleCache(ticketPojo);
						addToColorCache(ticketPojo);
						return ticketPojo;
					}
				}

			}

		}
		throw new ParkinSlotFullException("PARKING_FULL_EXCEPTION", "Parking slots not available");
	}

	public boolean updateReturnTicketToStore(TicketPojo returnTicket) throws Exception {

		if (returnTicket == null) {
			throw new Exception("Invalid returnTicket");
		}
		ParkingPojo parking = getParkingDetails();
		if (parking == null) {
			throw new Exception("Invalid Parking Object");
		}
		List<LevelPojo> levels = parking.getLevels();
		if (levels == null) {
			throw new Exception("Invalid Levels Object");
		}
		for (LevelPojo level : levels) {

			List<SlotsPojo> slots = level.getSlots();
			if (slots == null) {
				continue;
			}
			for (SlotsPojo slot : slots) {
				if (!slot.isAvailabilty() && slot.getSlotId() == returnTicket.getSlotNo()) {
					slot.setAvailabilty(true);
					slot.setVehicle(null);
					level.increaseSlotCount();
					removeFromIssuedTickets(slot.getSlotId());
					removeFromVehicleCache(returnTicket.getRegisterationNo());
					removeFromColorCache(returnTicket.getColour(), returnTicket.getRegisterationNo());
					return true;
				}
			}

		}
		return false;

	}

	public int findSlotNoByRegistrationNo(String registerationNo) throws VehicleSearchException {
		TicketPojo ticket = vehicleCache.get(registerationNo);
		if (ticket != null) {
			return ticket.getSlotNo();
		}
		throw new VehicleSearchException("NO_VEHICLE_FOUND", "Vehicle not found for search key");
	}

	public List<String> findAllRegistrationNoByColor(String color) throws VehicleSearchException {
		Map<String, TicketPojo> registrations = colorCache.get(color);
		List<String> ids = new ArrayList<String>();
		if (registrations == null) {
			throw new VehicleSearchException("NO_REGISTRATION_FOUND", "NO Registration found for given color");
		}
		for (String key : registrations.keySet()) {
			if (registrations.get(key) != null) {
				ids.add(registrations.get(key).getRegisterationNo());
			}
		}
		if (ids.isEmpty()) {
			throw new VehicleSearchException("NO_REGISTRATION_FOUND", "NO Registration found for given color");
		}
		return ids;
	}

	public List<Integer> findAllSlotNoByColor(String color) throws VehicleSearchException {
		Map<String, TicketPojo> registrations = colorCache.get(color);
		if (registrations == null) {
			throw new VehicleSearchException("NO_SLOTS_FOUND", "NO Slots found for given color");
		}
		List<Integer> ids = new ArrayList<Integer>();
		for (String key : registrations.keySet()) {
			if (registrations.get(key) != null) {
				ids.add(registrations.get(key).getSlotNo());
			}

		}
		if (ids.isEmpty()) {
			throw new VehicleSearchException("NO_SLOTS_FOUND", "NO Slots found for given color");
		}
		return ids;
	}

	public List<TicketPojo> getParkingStatus() {
		List<TicketPojo> tickets = new ArrayList<TicketPojo>();
		for (Integer slot : issuedTickets.keySet()) {
			tickets.add(issuedTickets.get(slot));
		}
		return tickets;
	}

	public void initalizeParkingSlots(int noOfSlots) {

		if (noOfSlots > 0 && parkingObj != null) {
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
		}

	}

	public ParkingPojo getParkingDetails() {

		return parkingObj;
	}

	private void addToIssuedTickets(TicketPojo tickets) {
		issuedTickets.put(tickets.getSlotNo(), tickets);
	}

	private void removeFromIssuedTickets(int slotNo) {
		issuedTickets.remove(slotNo);
	}

	private void addToVehicleCache(TicketPojo tickets) {
		vehicleCache.put(tickets.getRegisterationNo(), tickets);
	}

	private void removeFromVehicleCache(String registrationNo) {
		vehicleCache.remove(registrationNo);
	}

	private void addToColorCache(TicketPojo ticket) {
		Map<String, TicketPojo> vehicle = colorCache.get(ticket.getColour());
		if (vehicle == null) {
			Map<String, TicketPojo> newEntry = new HashMap<String, TicketPojo>();
			newEntry.put(ticket.getRegisterationNo(), ticket);
			colorCache.put(ticket.getColour(), newEntry);
		} else {
			vehicle.put(ticket.getRegisterationNo(), ticket);
			colorCache.put(ticket.getColour(), vehicle);
		}

	}

	private void removeFromColorCache(String color, String registrationNo) {
		Map<String, TicketPojo> vehicle = colorCache.get(color);
		if (vehicle != null) {
			vehicle.remove(registrationNo);
		}
	}

}
