package com.minidrobox.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minidrobox.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByEmail(String email);
	public Optional<Usuario> findByLogin(String login);

}
