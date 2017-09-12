package com.parking.exception;

public class ParkingSlotException  extends Exception{

	public ParkingSlotException(String code,String message){
		this.message = message;
		this.code =code;
	}
	
	private String message;
	private String code;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
}
