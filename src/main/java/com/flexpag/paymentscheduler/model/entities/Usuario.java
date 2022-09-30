package com.flexpag.paymentscheduler.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
@Entity
public class Usuario implements Serializable, UserDetails{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String nameUser;
	private String email; 
	private String senha;
	@OneToMany (mappedBy = "usuario")
	private List<Payment> payments = new ArrayList<>();
	@ManyToOne
	private Perfil perfis;
	
	public Usuario() {
		
	}
	
	public Usuario(String email, String senha) {
		this.email = email;
		this.senha = senha;
		this.perfis = new Perfil(1L, "ADM");
	}
	
	
	public Usuario(String nome, String nameUser, String email, String senha) {
		this.nome = nome;
		this.nameUser = nameUser;
		this.email = email;
		this.senha = senha;
		this.perfis = new Perfil(2L, "COMUM");
	}
		
	
	public void setRespostas(List<Payment> payments) {
		this.payments.addAll(payments);
	}
	
	public void setRespostas(Payment payment) {
		this.payments.add(payment);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(this.perfis);
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


}