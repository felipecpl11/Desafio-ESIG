package service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import model.Usuario;

public class UsuarioService {

	public static String Post(String login, String senha) throws URISyntaxException {
		HttpEntity<Usuario> request = new HttpEntity<>(new Usuario(login, senha));
		URI url = new URI("http://127.0.0.1:8080/mindropbox/login");

		RestTemplate restTemplate = new RestTemplate();
		try {
			//String response = restTemplate.postForObject(url, request, String.class);
			ResponseEntity<Usuario> response = restTemplate.exchange(url, HttpMethod.POST, request, Usuario.class);
			System.out.println(response.getStatusCode());
			return response.getStatusCode().toString();
		} catch (HttpClientErrorException e) {
			System.out.println(e.getStatusText());
			System.out.println(e.getStatusCode());
			System.out.println(e.getResponseBodyAsString());
			return e.getStatusCode().toString();
		}
	}
}
