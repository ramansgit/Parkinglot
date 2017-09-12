package com.parking.ticket.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.parking.config.ParkingSlotConstants;
import com.parking.exception.ParkingSlotException;
import com.parking.exception.SearchException;
import com.parking.model.LevelPojo;
import com.parking.model.ParkingPojo;
import com.parking.model.SlotsPojo;
import com.parking.model.TicketPojo;
import com.parking.model.VehiclePojo;
import com.parking.ticket.manager.ParkingManagerService;

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

	/**
	 * global parking obj which stores the parking setup and gets updated when
	 * vehicle parked/unparked.
	 */
	private static ParkingPojo parkingObj = new ParkingPojo();

	/**
	 * in memory issuedTicketsCache which stores all issued tickets. this is for
	 * returning the current status of the system. used
	 * Collections.synchronizedMap to ensure its thread safe. since vehicle will
	 * be parked/unparked 1 at a time ensuring synchronization for thread
	 * safety.
	 */
	private static Map<Integer, TicketPojo> issuedTicketsCache = Collections
			.synchronizedMap(new HashMap<Integer, TicketPojo>());
	/**
	 * in memory vehicle cache for storing tickets by registration no, this is
	 * issued for searching by registration no.
	 */
	private static Map<String, TicketPojo> registrationCache = Collections
			.synchronizedMap(new HashMap<String, TicketPojo>());

	/**
	 * in memory color cache for searching by color. in the system
	 */
	private static Map<String, Map<String, TicketPojo>> colorCache = Collections
			.synchronizedMap(new HashMap<String, Map<String, TicketPojo>>());

	/**
	 * follows singleton pattern, uses double checking approach to ensure thread
	 * safe.
	 * 
	 * @return
	 */
	public static ParkingStore getParkingStoreInstance() {
		if (parkingStoreInstance == null) {
			synchronized (ParkingManagerService.class) {
				if (parkingStoreInstance == null) {
					parkingStoreInstance = new ParkingStore();
				}
			}
		}
		return parkingStoreInstance;
	}

	/**
	 * constructor is private so no one can create object.
	 */
	private ParkingStore() {

	}

	/**
	 * when vehicle arrives at parking mall , checks for available slot and
	 * returns slot no. if no slot is available throws slotfull exception
	 */
	public synchronized TicketPojo getParkingSlot(VehiclePojo vehicle) throws ParkingSlotException, Exception {

		TicketPojo ticketPojo = new TicketPojo();

		int slotNo = 0;
		if (vehicle == null) {
			throw new Exception(ParkingSlotConstants.BAD_REQUEST_MSG);
		}
		ParkingPojo parking = getParkingDetails();
		if (parking == null) {
			throw new ParkingSlotException(ParkingSlotConstants.PARKING_SYSTEM_NOT_AVAILABLE_CODE,
					ParkingSlotConstants.PARKING_SYSTEM_NOT_AVAILABLE_MSG);
		}
		List<LevelPojo> levels = parking.getLevels();
		if (levels == null) {
			throw new ParkingSlotException(ParkingSlotConstants.PARKING_SYSTEM_NOT_AVAILABLE_CODE,
					ParkingSlotConstants.PARKING_SYSTEM_NOT_AVAILABLE_MSG);
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
						addToRegistrationCache(ticketPojo);
						addToColorCache(ticketPojo);
						return ticketPojo;
					}
				}

			}

		}
		throw new ParkingSlotException(ParkingSlotConstants.PARKING_FULL_EXCEPTION_CODE,
				ParkingSlotConstants.PARKING_FULL_EXCEPTION_MSG);
	}

	/**
	 * when driver returns the ticket at exit the manager frees the slot from
	 * the system, so that new vehicle can use it.
	 */
	public synchronized boolean updateReturnTicketToStore(TicketPojo returnTicket)
			throws ParkingSlotException, Exception {

		if (returnTicket == null) {
			throw new Exception(ParkingSlotConstants.BAD_REQUEST_MSG);
		}
		ParkingPojo parking = getParkingDetails();
		if (parking == null) {
			throw new ParkingSlotException(ParkingSlotConstants.PARKING_SYSTEM_NOT_AVAILABLE_CODE,
					ParkingSlotConstants.PARKING_SYSTEM_NOT_AVAILABLE_MSG);
		}
		List<LevelPojo> levels = parking.getLevels();
		if (levels == null) {
			throw new ParkingSlotException(ParkingSlotConstants.PARKING_SYSTEM_NOT_AVAILABLE_CODE,
					ParkingSlotConstants.PARKING_SYSTEM_NOT_AVAILABLE_MSG);
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
					removeFromRegistrationCache(returnTicket.getRegisterationNo());
					removeFromColorCache(returnTicket.getColour(), returnTicket.getRegisterationNo());
					return true;
				}
			}

		}
		return false;

	}

	/**
	 * find slot by registration no.
	 */
	public int findSlotNoByRegistrationNo(String registerationNo) throws SearchException {
		TicketPojo ticket = registrationCache.get(registerationNo);
		if (ticket != null) {
			return ticket.getSlotNo();
		}
		throw new SearchException(ParkingSlotConstants.SEARCH_DATA_NOT_FOUND_CODE,
				ParkingSlotConstants.SEARCH_DATA_NOT_FOUND_MSG);
	}

	/**
	 * find registration no by color
	 */
	public List<String> findAllRegistrationNoByColor(String color) throws SearchException {
		Map<String, TicketPojo> registrations = colorCache.get(color);
		List<String> ids = new ArrayList<String>();
		if (registrations == null) {
			throw new SearchException(ParkingSlotConstants.SEARCH_DATA_NOT_FOUND_CODE,
					ParkingSlotConstants.SEARCH_DATA_NOT_FOUND_MSG);
		}
		for (String key : registrations.keySet()) {
			if (registrations.get(key) != null) {
				ids.add(registrations.get(key).getRegisterationNo());
			}
		}
		if (ids.isEmpty()) {
			throw new SearchException(ParkingSlotConstants.SEARCH_DATA_NOT_FOUND_CODE,
					ParkingSlotConstants.SEARCH_DATA_NOT_FOUND_MSG);
		}
		return ids;
	}

	/**
	 * this method searches for slot no by color
	 */
	public List<Integer> findAllSlotNoByColor(String color) throws SearchException {
		Map<String, TicketPojo> registrations = colorCache.get(color);
		if (registrations == null) {
			throw new SearchException(ParkingSlotConstants.SEARCH_DATA_NOT_FOUND_CODE,
					ParkingSlotConstants.SEARCH_DATA_NOT_FOUND_MSG);
		}
		List<Integer> ids = new ArrayList<Integer>();
		for (String key : registrations.keySet()) {
			if (registrations.get(key) != null) {
				ids.add(registrations.get(key).getSlotNo());
			}

		}
		if (ids.isEmpty()) {
			throw new SearchException(ParkingSlotConstants.SEARCH_DATA_NOT_FOUND_CODE,
					ParkingSlotConstants.SEARCH_DATA_NOT_FOUND_MSG);
		}
		return ids;
	}

	/**
	 * this method returns all existing issued tickets from system.
	 */
	public List<TicketPojo> getParkingStatus() {
		List<TicketPojo> tickets = new ArrayList<TicketPojo>();
		for (Integer slot : issuedTicketsCache.keySet()) {
			tickets.add(issuedTicketsCache.get(slot));
		}
		return tickets;
	}

	/**
	 * this method is responsible for creating parking object and creates slots
	 * as per the input.
	 */
	public synchronized void setUpMultiStoreyParking(int noOfSlots) {
		if (noOfSlots > 0 && parkingObj != null) {
			parkingObj.setParkingName("MultiStoreParkingMall");
			int max_slots_per_level = ParkingSlotConstants.MAX_DEFAULT_SLOTS_PER_LEVEL;
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

	/**
	 * returns parking details
	 */
	public ParkingPojo getParkingDetails() {

		return parkingObj;
	}

	/**
	 * add data to issued ticket cache
	 * 
	 * @param tickets
	 */
	private void addToIssuedTickets(TicketPojo tickets) {
		issuedTicketsCache.put(tickets.getSlotNo(), tickets);
	}

	/**
	 * remove data from issued ticket cache
	 * 
	 * @param slotNo
	 */
	private void removeFromIssuedTickets(int slotNo) {
		issuedTicketsCache.remove(slotNo);
	}

	/**
	 * add data to registration cache
	 * 
	 * @param tickets
	 */
	private void addToRegistrationCache(TicketPojo tickets) {
		registrationCache.put(tickets.getRegisterationNo(), tickets);
	}

	/**
	 * remove from registration cache
	 * 
	 * @param registrationNo
	 */
	private void removeFromRegistrationCache(String registrationNo) {
		registrationCache.remove(registrationNo);
	}

	/**
	 * add data to color cache for search
	 * 
	 * @param ticket
	 */
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

	/**
	 * remove data from color cache.
	 * 
	 * @param color
	 * @param registrationNo
	 */
	private synchronized void removeFromColorCache(String color, String registrationNo) {
		Map<String, TicketPojo> vehicle = colorCache.get(color);
		if (vehicle != null) {
			vehicle.remove(registrationNo);
		}
	}

}
