package com.flexpag.paymentscheduler.controller.DTO;

import lombok.Data;

@Data
public class TokenDto {

	private String token;
	private String tipo;

	public TokenDto(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
	}
	
}
