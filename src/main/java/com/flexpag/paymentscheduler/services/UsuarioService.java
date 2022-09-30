package com.flexpag.paymentscheduler.services;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.flexpag.paymentscheduler.model.entities.Usuario;
import com.flexpag.paymentscheduler.repositories.UsuarioRepository;
import com.flexpag.paymentscheduler.services.DTO.UsuarioDTO;
import com.flexpag.paymentscheduler.services.form.UsuarioForm;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public ResponseEntity<UsuarioDTO> createUsuario(UsuarioForm form, UriComponentsBuilder uriBuilder) {
		Usuario usuario = usuarioRepository.save(form.converter());
		 URI uri = uriBuilder.path("/user/{id}").buildAndExpand(usuario.getId()).toUri();
		 return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
	}


	public ResponseEntity<UsuarioDTO> findById(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return ResponseEntity.ok(new UsuarioDTO(usuario.get()));
	}
	

}
