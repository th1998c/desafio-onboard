package com.flexpag.paymentscheduler.resources.DTO;

import lombok.Data;

@Data
public class ErroFormDTO {
	
	private String path;
	private String erro;
	
	public ErroFormDTO(String campo, String erro) {
		this.path = campo;
		this.erro = erro;
	}

}
