package com.flexpag.paymentscheduler.controller.DTO;

import lombok.Data;

@Data
public class ErroFormDTO {
	
	private String campo;
	private String erro;
	
	public ErroFormDTO(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

}
