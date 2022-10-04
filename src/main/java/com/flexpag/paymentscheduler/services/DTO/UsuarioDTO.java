package com.flexpag.paymentscheduler.services.DTO;

import com.flexpag.paymentscheduler.model.entities.Usuario;

import lombok.Getter;

@Getter
public class UsuarioDTO {
	
	private String email;
	private String nome;
	private String nameUser;
	
	
	
	public UsuarioDTO(Usuario user) {
		this.email = user.getEmail();
		this.nome = user.getNome();
		this.nameUser = user.getNameUser();
	}
	

}
