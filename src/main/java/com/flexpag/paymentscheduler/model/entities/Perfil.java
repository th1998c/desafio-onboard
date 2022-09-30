package com.flexpag.paymentscheduler.model.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
@Entity
public class Perfil implements GrantedAuthority, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public Perfil() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.nome;
	}

	public Perfil(long id, String nome) {
		this.id = id;
		this.nome = nome;
	}


}
