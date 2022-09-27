package com.flexpag.paymentscheduler.services.exceptions;

public class InvalidDateException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidDateException(Object id) {
		super("A nova data de pagamento precisa ser antes da data atual.");
	}
	
}