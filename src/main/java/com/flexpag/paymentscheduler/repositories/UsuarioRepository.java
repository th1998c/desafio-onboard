package com.flexpag.paymentscheduler.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flexpag.paymentscheduler.model.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String username);

}
