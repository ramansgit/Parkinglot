package com.parking.app;

import com.parking.util.ParkingSlotUtil;

/**
 * main class for running the app.
 * 
 * @author ramans
 *
 */
public class ParkingAppExecutor {

	public static void main(String[] args) {
		// execute via file input
		if (args != null && args.length > 0) {
			String fileName = args[0];
			if (fileName != null && !fileName.isEmpty()) {
				ParkingSlotUtil.executeFileInputs(fileName);

			}
		} else {// execution via commands
			ParkingSlotUtil.executeCommandInputs();
		}

	}

}
