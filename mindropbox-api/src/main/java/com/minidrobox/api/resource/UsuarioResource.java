package com.minidrobox.api.resource;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.mockito.internal.stubbing.answers.ThrowsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minidrobox.api.model.Usuario;
import com.minidrobox.api.repository.UsuarioRepository;

@RestController
@RequestMapping("/mindropbox/login")
public class UsuarioResource {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@PostMapping
	public ResponseEntity<?> serachByCredentials(@RequestBody Usuario usuario){
		Optional<Usuario> usarioOptional = usuarioRepository.findByLogin(usuario.getLogin());
		Usuario user = usarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário não existe"));
		
		
		if(encoder(usuario.getSenha(), user.getSenha())) {
			System.out.println("SHOW!");
			return ResponseEntity.ok().build();
		}
		else {
			System.out.println("ERRO");
			return ResponseEntity.notFound().build();
		}
	}
	
	public boolean encoder(String senha, String encoded) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(senha, encoded);
	}
	
}
