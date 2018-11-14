package com.minidrobox.api.repository.filter;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class FileFilter {

	private String description;
	
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime criadoDe;
	
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime criadoAte;
	
	private String author;
	
	private String file_name;
	
	private String extension;
	
	private String usuarioLogin;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getCriadoDe() {
		return criadoDe;
	}
	public void setCriadoDe(LocalDateTime criadoDe) {
		this.criadoDe = criadoDe;
	}
	public LocalDateTime getCriadoAte() {
		return criadoAte;
	}
	public void setCriadoAte(LocalDateTime criadoAte) {
		this.criadoAte = criadoAte;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getUsuarioLogin() {
		return usuarioLogin;
	}
	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}
}
