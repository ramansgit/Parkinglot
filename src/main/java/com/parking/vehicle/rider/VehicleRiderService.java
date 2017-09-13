package com.parking.vehicle.rider;

import com.parking.model.ParkingTicket;
import com.parking.model.Vehicle;
import com.parking.ticket.manager.ParkingManagerInterface;
import com.parking.ticket.manager.ParkingManagerService;

/**
 * Vehicle Rider Service allows the driver to park on the given slot and unPark from the parked place.
 * @author ramans
 *
 */
public class VehicleRiderService implements VehicleRiderInterface {

	/**
	 * allows driver to park the vehicle on the given slot, if no slot available exception will be thrown.
	 */
	public  ParkingTicket parkVehicle(Vehicle pojo) throws Exception {
		ParkingManagerInterface parkingManager = ParkingManagerService.getParkingManager();
		return parkingManager.issueParkingTiketAtEntrance(pojo);
	}

	/**
	 * allows driver to take the vehicle from the parking area.
	 */
	public  void unParkVehicle(ParkingTicket pojo) throws Exception {

		ParkingManagerInterface parkingManager = ParkingManagerService.getParkingManager();
		parkingManager.collectParkingTicketAtExit(pojo);
	}

}
