package com.flexpag.paymentscheduler.controller.DTO;

import lombok.Getter;

@Getter
public class TokenDto {

	private String access_token;
	private String token_type;
	private String expires_in;

	public TokenDto(String access_token, String token_type, String expires_in) {
		this.access_token = access_token;
		this.token_type = token_type;
		this.expires_in = expires_in;
	}
	
}
