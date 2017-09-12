package test.parking.ticket.store;

import com.parking.model.TicketPojo;
import com.parking.model.VehiclePojo;
import com.parking.ticket.manager.ParkingManagerInterface;
import com.parking.ticket.manager.ParkingManagerService;

public class TestParkingStore {

	public void testAddSlots() {

		ParkingManagerInterface ticketStore = ParkingManagerService.getTicketManagerInstance();

		System.out.println(ticketStore);
		ticketStore.initailizeParkingSlots(8);
	}

	public void testIssueTicketAtEntrance() throws Exception {

		ParkingManagerInterface ticketStore = ParkingManagerService.getTicketManagerInstance();

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

	}

	public void testGetParkingObj() {
		ParkingManagerInterface ticketStore = ParkingManagerService.getTicketManagerInstance();
		System.out.println("parking object" + ticketStore.getParkingObj());

	}

	public static void main(String[] args) throws Exception {
		new TestParkingStore().testAddSlots();
		new TestParkingStore().testIssueTicketAtEntrance();
		new TestParkingStore().testGetParkingObj();
	}

}
