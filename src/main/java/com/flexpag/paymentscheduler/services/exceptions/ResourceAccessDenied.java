package com.flexpag.paymentscheduler.services.exceptions;

public class ResourceAccessDenied extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceAccessDenied(Object id) {
		super("Acesso negado para deletar ou alterar, pagamento já efetuado.");
	}
	
}