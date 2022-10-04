package com.flexpag.paymentscheduler.controller.DTO;

import lombok.Getter;

@Getter
public class ErroFormDTO {
	
	private String campo;
	private String erro;
	
	public ErroFormDTO(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

}
