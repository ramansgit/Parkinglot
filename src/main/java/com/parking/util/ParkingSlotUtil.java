package com.parking.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.metal.MetalBorders.TableHeaderBorder;

import com.parking.exception.SearchException;
import com.parking.model.TicketPojo;
import com.parking.model.VehiclePojo;
import com.parking.ticket.manager.ParkingManagerInterface;
import com.parking.ticket.manager.ParkingManagerService;
import com.parking.vehicle.rider.VehicleRiderInterface;
import com.parking.vehicle.rider.VehicleRiderService;

public class ParkingSlotUtil {

	/**
	 * execute instructions passed from file.
	 * 
	 * @param fileName
	 */
	public static void executeFileInputs(String fileName) {
		BufferedReader buferReader = null;
		try {
			buferReader = new BufferedReader(new FileReader(fileName));
			String inputString;
			while ((inputString = buferReader.readLine()) != null) {
				try {
					executeInstructions(inputString);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buferReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * execute instruction
	 * 
	 * @param inputString
	 * @throws Exception
	 */
	private static void executeInstructions(String inputString) throws Exception {
		VehicleRiderInterface riderService = new VehicleRiderService();
		ParkingManagerInterface parkingManger = ParkingManagerService.getParkingManager();
		if (inputString == null || inputString.isEmpty()) {
			throw new Exception("Bad Input String");
		}
		String split[] = inputString.split(" ");
		if (inputString.startsWith("create_parking_lot")) {
			parkingManger.setUpMultiStoreyParking(Integer.parseInt(split[1]));
			System.out.println("Created a parking lot with " + split[1] + " slots ");

		} else if (inputString.startsWith("park")) {
			VehiclePojo vehicle = new VehiclePojo();
			vehicle.setColour(split[2]);
			vehicle.setRegisterationNo(split[1]);
			try {
				TicketPojo ticket = riderService.parkVehicle(vehicle);
				System.out.println("Allocated slot number: " + ticket.getSlotNo());
			} catch (Exception e) {
				System.out.println("Sorry, parking lot is full ");
			}

		} else if (inputString.startsWith("leave")) {
			TicketPojo ticket = parkingManger.getTicketInfoBySlot(Integer.parseInt(split[1]));
			try {
				if (ticket != null) {
					riderService.unParkVehicle(ticket);
				}
				System.out.println("Slot number " + split[1] + " is free");
			} catch (Exception e) {
			}

		} else if (inputString.startsWith("status")) {
			List<TicketPojo> tickets = parkingManger.getParkingStatus();

			System.out.println(String.format("%2s %15s %8s", "Slot No", "Registration No", "Colour"));
			for (int row = 0; row < tickets.size(); row++) {

				System.out.println(String.format("%2s %19s %8s", tickets.get(row).getSlotNo(),
						tickets.get(row).getRegisterationNo(), tickets.get(row).getColour()));
			}

		} else if (inputString.startsWith("registration_numbers_for_cars_with_colour")) {
			try {
				List<String> registrations = parkingManger.findAllRegistrationNoByColor(split[1]);
				StringBuffer builder = new StringBuffer();
				for (String registration : registrations) {
					builder.append(registration).append(",");

				}
				builder.deleteCharAt(builder.length() - 1);
				System.out.println(builder);

			} catch (SearchException e) {
				System.out.println("Not found");
			}

		} else if (inputString.startsWith("slot_numbers_for_cars_with_colour")) {
			try {

				List<Integer> slotNos = parkingManger.findAllSlotNoByColor(split[1]);
				StringBuffer builder = new StringBuffer();
				for (Integer slotNo : slotNos) {
					builder.append(slotNo).append(",");

				}
				builder.deleteCharAt(builder.length() - 1);
				System.out.println(builder);

			} catch (SearchException e) {
				System.out.println("Not found");
			}

		} else if (inputString.startsWith("slot_number_for_registration_number")) {
			try {
				System.out.println(parkingManger.findSlotNoByRegistrationNo(split[1]));
			} catch (SearchException e) {
				System.out.println("Not found");
			}

		}

	}

	/**
	 * execute instructions passed from command line
	 */
	public static void executeCommandInputs() {

		System.out.println("Please enter inputs ");
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String inputString = sc.nextLine();
			try {
				executeInstructions(inputString);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		sc.close();
	}
}
