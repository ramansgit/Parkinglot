package com.parking.vehicle.rider;

import com.parking.model.TicketPojo;
import com.parking.model.VehiclePojo;

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
	public abstract TicketPojo parkVehicle(VehiclePojo pojo) throws Exception;

	/**
	 * un park vehicle
	 * @param pojo
	 * @throws Exception
	 */
	public abstract void unParkVehicle(TicketPojo pojo) throws Exception;
}
