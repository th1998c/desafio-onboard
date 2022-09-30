package com.flexpag.paymentscheduler.services.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.flexpag.paymentscheduler.model.entities.Usuario;

import lombok.Data;

@Data
public class UsuarioForm {
	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String nameUser;
	@NotNull @Email @NotEmpty
	private String email;
	@NotNull @NotEmpty
	private String senha;
	
	
	public Usuario converter() {
		return new Usuario(this.nome, this.nameUser, this.email, new BCryptPasswordEncoder().encode(this.senha));
	}
	
}
