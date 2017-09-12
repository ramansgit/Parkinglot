package com.parking.config;

public class ParkingSlotConstants {

	/**
	 * default slots per level. This is configurable property.
	 */
	public static final int MAX_DEFAULT_SLOTS_PER_LEVEL = 20;
	
	
	
	// code
	public static final String SEARCH_DATA_NOT_FOUND_CODE ="SEARCH_DATA_NOT_FOUND";
	public static final String PARKING_FULL_EXCEPTION_CODE="PARKING_FULL_EXCEPTION";
	public static final String PARKING_SYSTEM_NOT_AVAILABLE_CODE="PARKING_SYSTEM_NOT_AVAILABLE";
	
	public static final String BAD_REQUEST_CODE="BAD_REQUEST";
	
	
	// message
	public static final String SEARCH_DATA_NOT_FOUND_MSG="No data matched for given search key";
	public static final String PARKING_FULL_EXCEPTION_MSG="Sorry no slots avaialable,parking full.";
	
	public static final String PARKING_SYSTEM_NOT_AVAILABLE_MSG="Sorry parking system is down, please try later.";
	
	public static final String BAD_REQUEST_MSG="Bad request , please check the input data.";
}
