package com.parking.vehicle.rider;

import com.parking.model.TicketPojo;
import com.parking.model.VehiclePojo;
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
	 * making it thread safe to handle concurrency in the system.
	 */
	public synchronized TicketPojo parkVehicle(VehiclePojo pojo) throws Exception {
		ParkingManagerInterface parkingManager = ParkingManagerService.getParkingManager();
		return parkingManager.issueParkingTiketAtEntrance(pojo);
	}

	/**
	 * allows driver to take the vehicle from the parking area,making it thread safe to handle concurrency in the system. also in real time only one vehicle can come out atime.
	 */
	public synchronized void unParkVehicle(TicketPojo pojo) throws Exception {

		ParkingManagerInterface parkingManager = ParkingManagerService.getParkingManager();
		parkingManager.collectParkingTicketAtExit(pojo);
	}

}
