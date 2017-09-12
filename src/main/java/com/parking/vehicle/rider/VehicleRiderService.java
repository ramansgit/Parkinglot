package com.parking.vehicle.rider;

import com.parking.model.TicketPojo;
import com.parking.model.VehiclePojo;
import com.parking.ticket.manager.ParkingManagerInterface;
import com.parking.ticket.manager.ParkingManagerService;

public class VehicleRiderService implements VehicleRiderInterface {

	public TicketPojo parkVehicle(VehiclePojo pojo) throws Exception {
		ParkingManagerInterface parkingManager = ParkingManagerService.getParkingManager();
		return parkingManager.issueParkingTiketAtEntrance(pojo);
	}

	public void takeVehicle(TicketPojo pojo) throws Exception {

		ParkingManagerInterface parkingManager = ParkingManagerService.getParkingManager();
		parkingManager.collectParkingTicketAtExit(pojo);
	}

}
