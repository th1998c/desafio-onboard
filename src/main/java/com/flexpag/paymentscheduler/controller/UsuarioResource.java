package com.flexpag.paymentscheduler.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.flexpag.paymentscheduler.services.UsuarioService;
import com.flexpag.paymentscheduler.services.DTO.PaymentDetalhesDTO;
import com.flexpag.paymentscheduler.services.DTO.UsuarioDTO;
import com.flexpag.paymentscheduler.services.form.UsuarioForm;

@RestController
@RequestMapping(value = "/user")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;	
	

	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDePagamentos", allEntries = true)
	public ResponseEntity<UsuarioDTO> create(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
		return usuarioService.createUsuario(form, uriBuilder);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
		return usuarioService.findById(id);
	}
	
	
}
