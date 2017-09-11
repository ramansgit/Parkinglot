package com.parking.ticket.store;

/**
 * TicketStore acts as in memory store for managing tickets in the system. This
 * store can be replaced with dao layer in required.
 * 
 * @author ramans
 *
 */
public class TicketStore implements TicketStoreInterface {

	/**
	 * global instance of TicketStore class , follows singleton design pattern.
	 */
	private static TicketStore ticketStoreInstance;

	/**
	 * follows singleton pattern, uses double checking approach to ensure thread
	 * safe.
	 * 
	 * @return
	 */
	public static TicketStore getTicketManagerInstance() {
		if (ticketStoreInstance == null) {
			synchronized (ticketStoreInstance) {
				ticketStoreInstance = new TicketStore();
			}
		}
		return ticketStoreInstance;
	}
	
	private TicketStore(){
		
	}

}
