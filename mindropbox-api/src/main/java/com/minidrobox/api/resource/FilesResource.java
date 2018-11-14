package com.minidrobox.api.resource;

import java.io.IOException;
import java.net.URI;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.minidrobox.api.model.File;
import com.minidrobox.api.model.Files;
import com.minidrobox.api.repository.FileRepository;
import com.minidrobox.api.repository.FilesRepository;
import com.minidrobox.api.repository.filter.FileFilter;
import com.minidrobox.api.service.FileService;

@RestController
@RequestMapping("/mindropbox")
public class FilesResource {

	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private FilesRepository filesRepository;
	
	@Autowired
	private FileService fileService;

	@PostMapping
	public ResponseEntity<File> submit(@Valid @ModelAttribute File file,
			@RequestParam("file") MultipartFile object, HttpServletResponse response) throws IOException {

		fileRepository.save(
				fileService.insert(file, object));
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(file.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());

		return ResponseEntity.status(HttpStatus.CREATED).body(file);

	}

	@GetMapping("/{codigo}")
	public ResponseEntity<File> searchById(@PathVariable long codigo, HttpServletResponse response) throws IOException {
		File file = fileRepository.findOne(codigo);
		//FUNCAO PARA ESCREVER ARQUIVO NO DIRETORIO
		//fileService.writeFile(file);
		return ResponseEntity.ok(file);
	}
	
	@GetMapping
	public Page<Files> search(FileFilter fileFilter, Pageable pageable){
		return filesRepository.filter(fileFilter, pageable);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<File> updateFileContent(@PathVariable Long id, @RequestParam("file") MultipartFile object) throws IOException{
		File file = fileService.updateFile(id, object);
		fileRepository.save(file);
		return ResponseEntity.ok(file);
	}
	
	@PutMapping("/metadata/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<File> updateMetadata(@PathVariable Long id, @RequestBody File file){
		file = fileService.updateMetada(id, file);
		return ResponseEntity.ok(file);
	}
	
	@PutMapping("/path/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<File> updatePath(@PathVariable Long id, @RequestBody File file){
		file = fileService.updatePath(id, file);
		return ResponseEntity.ok(file);
	}
	
	@PutMapping("/id/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<File> updateId(@PathVariable Long id, @RequestBody File file){
		file = fileService.updateId(id, file);
		return ResponseEntity.ok(file);
	}
	
	
	
}