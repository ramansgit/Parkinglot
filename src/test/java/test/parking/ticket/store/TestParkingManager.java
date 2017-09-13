package test.parking.ticket.store;

import org.testng.annotations.Test;

import com.parking.exception.SearchException;
import com.parking.model.ParkingTicket;
import com.parking.model.Vehicle;
import com.parking.ticket.manager.ParkingManagerInterface;
import com.parking.ticket.manager.ParkingManagerService;

public class TestParkingManager {

	@Test
	public void testAddSlots() {
		ParkingManagerInterface ticketStore = ParkingManagerService.getParkingManager();
		ticketStore.setUpMultiStoreyParking(30);
	}

	@Test(dependsOnMethods = { "testAddSlots" })
	public void testIssueTicketAtEntrance() throws Exception {

		ParkingManagerInterface ticketStore = ParkingManagerService.getParkingManager();

		Vehicle vehicle = new Vehicle();
		vehicle.setColour("Blue");
		vehicle.setRegisterationNo("KA-01HH6756");
		ParkingTicket t1 = ticketStore.issueParkingTiketAtEntrance(vehicle);
		System.out.println(t1);

		Vehicle vehicle1 = new Vehicle();
		vehicle1.setColour("Orange");
		vehicle1.setRegisterationNo("KA-01HH6757");
		ParkingTicket t2 = ticketStore.issueParkingTiketAtEntrance(vehicle1);
		System.out.println(t2);

		Vehicle vehicle2 = new Vehicle();
		vehicle2.setColour("Blue");
		vehicle2.setRegisterationNo("KA-01HH6758");
		ParkingTicket t3 = ticketStore.issueParkingTiketAtEntrance(vehicle2);
		System.out.println(t3);

		System.out.println("returning ticket at exit");
		testCollectReturnTicketAtExit(t3);

	}

	public void testCollectReturnTicketAtExit(ParkingTicket t3) throws Exception {
		ParkingManagerInterface ticketStore = ParkingManagerService.getParkingManager();
		ticketStore.collectParkingTicketAtExit(t3);
	}

	@Test(dependsOnMethods = { "testIssueTicketAtEntrance" })
	public void testGetParkingDetails() {
		ParkingManagerInterface ticketStore = ParkingManagerService.getParkingManager();
		System.out.println("parking object" + ticketStore.getParkingDetails());

	}

	@Test(dependsOnMethods = { "testIssueTicketAtEntrance" })
	public void testGetParkingStatus() {
		ParkingManagerInterface ticketStore = ParkingManagerService.getParkingManager();
		System.out.println("Issued Tickets" + ticketStore.getParkingStatus());

	}

	@Test(dependsOnMethods = { "testIssueTicketAtEntrance" })
	public void testFindSlotNoByRegistrationNo() throws SearchException {
		ParkingManagerInterface ticketStore = ParkingManagerService.getParkingManager();
		System.out.println("Slot No" + ticketStore.findSlotNoByRegistrationNo("KA-01HH6756"));

	}

	@Test(dependsOnMethods = { "testIssueTicketAtEntrance" })
	public void testFindAllRegistrationNoByColor() throws SearchException {
		ParkingManagerInterface ticketStore = ParkingManagerService.getParkingManager();
		System.out.println("Slot No" + ticketStore.findAllRegistrationNoByColor("Blue"));

	}

	@Test(dependsOnMethods = { "testIssueTicketAtEntrance" })
	public void testFindAllSlotNosByColor() throws SearchException {
		ParkingManagerInterface ticketStore = ParkingManagerService.getParkingManager();
		System.out.println("Slot No" + ticketStore.findAllSlotNoByColor("Blue"));

	}
	
	// public static void main(String[] args) throws Exception {
	// new TestParkingManager().testAddSlots();
	// new TestParkingManager().testIssueTicketAtEntrance();
	// new TestParkingManager().testParkVehicle();
	// new TestParkingManager().testGetParkingDetails();
	// new TestParkingManager().testGetParkingStatus();
	// new TestParkingManager().testFindSlotNoByRegistrationNo("KA-01HH6756");
	// new TestParkingManager().testFindAllRegistrationNoByColor("Orange");
	// new TestParkingManager().testFindAllSlotNosByColor("Blue");
	//
	// }

}
