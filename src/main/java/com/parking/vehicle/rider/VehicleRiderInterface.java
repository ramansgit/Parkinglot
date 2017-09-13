package com.parking.vehicle.rider;

import com.parking.model.ParkingTicket;
import com.parking.model.Vehicle;

/**
 * Vechcle driver will be interacting with the system through this interface
 * @author ramans
 *
 */
public interface VehicleRiderInterface {

	/**
	 * park vehicle
	 * @param pojo
	 * @return
	 * @throws Exception
	 */
	public abstract ParkingTicket parkVehicle(Vehicle pojo) throws Exception;

	/**
	 * un park vehicle
	 * @param pojo
	 * @throws Exception
	 */
	public abstract void unParkVehicle(ParkingTicket pojo) throws Exception;
}
