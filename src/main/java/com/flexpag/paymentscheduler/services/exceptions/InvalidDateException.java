package com.flexpag.paymentscheduler.services.exceptions;

public class InvalidDateException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidDateException(Object id) {
		super("The new payment date must be before the current date.");
	}
	
}