package com.parking.vehicle.rider;

import com.parking.model.TicketPojo;
import com.parking.model.VehiclePojo;

public interface VehicleRiderInterface {

	public abstract TicketPojo parkVehicle(VehiclePojo pojo);

	public abstract void takeVehicle(TicketPojo pojo);
}
