package test.parking.ticket.store;

import com.parking.exception.VehicleSearchException;
import com.parking.model.TicketPojo;
import com.parking.model.VehiclePojo;
import com.parking.ticket.manager.ParkingManagerInterface;
import com.parking.ticket.manager.ParkingManagerService;
import com.parking.vehicle.rider.VehicleRiderInterface;
import com.parking.vehicle.rider.VehicleRiderService;

public class TestParkingManager {

	public void testAddSlots() {

		ParkingManagerInterface ticketStore = ParkingManagerService.getParkingManager();

		System.out.println(ticketStore);
		ticketStore.initailizeParkingSlots(8);
	}

	public void testIssueTicketAtEntrance() throws Exception {

		ParkingManagerInterface ticketStore = ParkingManagerService.getParkingManager();

		VehiclePojo vehicle = new VehiclePojo();
		vehicle.setColour("Blue");
		vehicle.setRegisterationNo("KA-01HH6756");
		TicketPojo t1 = ticketStore.issueParkingTiketAtEntrance(vehicle);
		System.out.println(t1);

		VehiclePojo vehicle1 = new VehiclePojo();
		vehicle1.setColour("Orange");
		vehicle1.setRegisterationNo("KA-01HH6757");
		TicketPojo t2 = ticketStore.issueParkingTiketAtEntrance(vehicle1);
		System.out.println(t2);

		VehiclePojo vehicle2 = new VehiclePojo();
		vehicle2.setColour("Blue");
		vehicle2.setRegisterationNo("KA-01HH6758");
		TicketPojo t3 = ticketStore.issueParkingTiketAtEntrance(vehicle2);
		System.out.println(t3);

		System.out.println("returning ticket at exit");
		//testCollectReturnTicketAtExit(t3);

	}

	public void testCollectReturnTicketAtExit(TicketPojo t3) throws Exception {
		ParkingManagerInterface ticketStore = ParkingManagerService.getParkingManager();
		ticketStore.collectParkingTicketAtExit(t3);
	}

	public void testGetParkingDetails() {
		ParkingManagerInterface ticketStore = ParkingManagerService.getParkingManager();
		System.out.println("parking object" + ticketStore.getParkingDetails());

	}

	public void testGetParkingStatus() {
		ParkingManagerInterface ticketStore = ParkingManagerService.getParkingManager();
		System.out.println("Issued Tickets" + ticketStore.getParkingStatus());

	}

	public void testFindSlotNoByRegistrationNo(String registrationNo) throws VehicleSearchException {
		ParkingManagerInterface ticketStore = ParkingManagerService.getParkingManager();
		System.out.println("Slot No" + ticketStore.findSlotNoByRegistrationNo(registrationNo));

	}

	public void testFindAllRegistrationNoByColor(String color) throws VehicleSearchException {
		ParkingManagerInterface ticketStore = ParkingManagerService.getParkingManager();
		System.out.println("Slot No" + ticketStore.findAllRegistrationNoByColor(color));

	}

	public void testFindAllSlotNosByColor(String color) throws VehicleSearchException {
		ParkingManagerInterface ticketStore = ParkingManagerService.getParkingManager();
		System.out.println("Slot No" + ticketStore.findAllSlotNoByColor(color));

	}
	
	public void testParkVehicle() throws Exception{
		VehicleRiderInterface vehicleRider = new VehicleRiderService();

		VehiclePojo vehicle = new VehiclePojo();
		vehicle.setColour("Green");
		vehicle.setRegisterationNo("KA-01HH6758");
		TicketPojo t1 = vehicleRider.parkVehicle(vehicle);
		System.out.println(t1);

		VehiclePojo vehicle1 = new VehiclePojo();
		vehicle1.setColour("White");
		vehicle1.setRegisterationNo("KA-01HH6759");
		TicketPojo t2 = vehicleRider.parkVehicle(vehicle1);
		System.out.println(t2);

		VehiclePojo vehicle2 = new VehiclePojo();
		vehicle2.setColour("Blue");
		vehicle2.setRegisterationNo("KA-01HH6760");
		TicketPojo t3 = vehicleRider.parkVehicle(vehicle2);
		System.out.println(t3);
		
		System.out.println("Unparked vehicle t3");
		//testUnParkVehicle(t3);

	}
	
	public void testUnParkVehicle(TicketPojo t3) throws Exception {
		VehicleRiderInterface vehicleRider = new VehicleRiderService();
		vehicleRider.takeVehicle(t3);
	}

	public static void main(String[] args) throws Exception {
		new TestParkingManager().testAddSlots();
		new TestParkingManager().testIssueTicketAtEntrance();	
		new TestParkingManager().testParkVehicle();
		new TestParkingManager().testGetParkingDetails();
		new TestParkingManager().testGetParkingStatus();
		new TestParkingManager().testFindSlotNoByRegistrationNo("KA-01HH6756");
		new TestParkingManager().testFindAllRegistrationNoByColor("Orange");
		new TestParkingManager().testFindAllSlotNosByColor("Blue");

	}

}
