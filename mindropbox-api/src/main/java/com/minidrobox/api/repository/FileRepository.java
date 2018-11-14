package com.minidrobox.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minidrobox.api.model.File;

public interface FileRepository extends JpaRepository<File, Long>{
	
	public Optional<File> findByUsuarioLogin(String usuarioLogin);
}
