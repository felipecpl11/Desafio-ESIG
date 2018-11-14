package com.minidrobox.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.minidrobox.api.model.File;
import com.minidrobox.api.model.Usuario;
import com.minidrobox.api.repository.FileRepository;
import com.minidrobox.api.repository.UsuarioRepository;

@Service
public class FileService {

	@Autowired
	FileRepository arquivoRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public File insert(File arquivo, MultipartFile file) throws IOException {
		
		byte[] arrayPic = file.getBytes();
		if(file.getOriginalFilename().length() > 200)
			throw new DataIntegrityViolationException("O nome do de conter menos de 200 caracteres");
		arquivo.setFile_name(file.getOriginalFilename());
		
		arquivo.setExtension("." +arquivo.getFile_name().substring(arquivo.getFile_name().lastIndexOf(".") + 1));

		arquivo.setContent(arrayPic);
		
		//arquivo.setUsuario_login(arquivo.getUsuario_login());
		String login = arquivo.getUsuarioLogin();
		Optional<Usuario> usarioOptional = usuarioRepository.findByLogin(login);
		Usuario usuario = usarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		
		arquivo.setUsuarioLogin(usuario.getLogin());
		
		/*
		if (arquivoRepository.exists(arquivo.getId())) {
			throw new DuplicateKeyException("Id informado já existente");
		}
		else
		*/
		return arquivo;
	}
	
	public File updateFile(long  id, MultipartFile object) throws IOException {
		
		File file = arquivoRepository.findOne(id);
		if (file == null)
				throw new EmptyResultDataAccessException(1);
		
		byte[] arrayPic = object.getBytes();
		
		file.setFile_name(object.getOriginalFilename());
		
		file.setExtension("." +file.getFile_name().substring(file.getFile_name().lastIndexOf(".") + 1));

		file.setContent(arrayPic);
		
		return file;
	}
	
	public void writeFile (File arquivo) throws IOException {
		//String caminho = "C:\\Users\\Felipe Leão\\Documents\\workspace-sts-3.9.0.RELEASE\\mindropbox-api\\"
		String caminho = arquivo.getPath() + "\\" + arquivo.getFile_name();
		Files.write(Paths.get(caminho), arquivo.getContent(), StandardOpenOption.CREATE);
	}

	public File updateMetada(long  id, File file) {
		
		File savedFile = arquivoRepository.findOne(id);
		if (savedFile == null)
			throw new EmptyResultDataAccessException(1);
		
		//BeanUtils.copyProperties(file, savedFile, "id","content","extension","created","file_name","path");
		if(file.getAuthor() != null)
			savedFile.setAuthor(file.getAuthor());
		if(file.getDescription() != null)
			savedFile.setDescription(file.getDescription());
		
		return arquivoRepository.save(savedFile);
	}
	
	public File updatePath(long  id, File file) {
		
		File savedFile = arquivoRepository.findOne(id);
		if (savedFile == null)
			throw new EmptyResultDataAccessException(1);
		
		savedFile.setPath(file.getPath());
		
		return arquivoRepository.save(savedFile);
	}
	
	public File updateId(long  id, File file) {
		
		File savedFile = arquivoRepository.findOne(id);
		if (savedFile == null)
			throw new EmptyResultDataAccessException(1);
		
		//BeanUtils.copyProperties(file, savedFile, "id","content","extension","created","file_name","path");
		
		savedFile.setId(file.getId());
		return arquivoRepository.save(savedFile);
	}
}
