package service;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;

import org.primefaces.model.UploadedFile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.UsuarioBean;
import model.File;
import pojo.Arquivo;
import pojo.Arquivos;
import pojo.Content;

public class ServiceArquivo implements Serializable {

	private static final long serialVersionUID = 1L;

	ObjectMapper objectMapper = new ObjectMapper();

	public static void main(String args[]) throws URISyntaxException, IOException {

		//GetAll();
	}

	public static List<Content> GetAll() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		UsuarioBean usuarioBean = UsuarioBean.getInstance();
		//String encoding = Base64.getEncoder().encodeToString("admin@algamoney.com:admin".getBytes("utf-8"));
		String encoding = Base64.getEncoder().encodeToString(
				(usuarioBean.getUsuario().getLogin()+":"+usuarioBean.getUsuario().getSenha()).getBytes("utf-8"));
		URL url = new URL("http://127.0.0.1:8080/mindropbox");
		URLConnection connection = url.openConnection();
		connection.setRequestProperty("Authorization", "Basic " + encoding);

		Arquivos arquivos = objectMapper.readValue(connection.getInputStream(), Arquivos.class);
		System.out.println(arquivos.getSize());
		return arquivos.getContent(); 
	}
	
	public static void Post(UploadedFile uploadedFile) throws IOException, URISyntaxException {
		UsuarioBean usuarioBean = UsuarioBean.getInstance();
		String encoding = Base64.getEncoder().encodeToString(
				(usuarioBean.getUsuario().getLogin()+":"+usuarioBean.getUsuario().getSenha()).getBytes("utf-8"));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.add("Authorization", "Basic " + encoding);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();		
		body.add("file", uploadToPost(uploadedFile));
		body.add("usuarioLogin", usuarioBean.getUsuario().getLogin());
		body.add("path", "Caminho qualquer");

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		URI url = new URI("http://127.0.0.1:8080/mindropbox");
		RestTemplate restTemplate = new RestTemplate();
		try {
			ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
			System.out.println("Response code: " + response.getStatusCode());
		} catch (org.springframework.web.client.HttpClientErrorException e) {
			System.out.println(e.getResponseBodyAsString());
		}
	}

	public static void Get() throws URISyntaxException, UnsupportedEncodingException {
		String encoding = Base64.getEncoder().encodeToString("admin@algamoney.com:admin".getBytes("utf-8"));

		ObjectMapper objectMapper = new ObjectMapper();
		URI url = new URI("http://127.0.0.1:8080/mindropbox/0");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + encoding);
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<File> response = restTemplate.exchange(url, HttpMethod.GET, request, File.class);
	}

	public static void Get2() throws URISyntaxException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String encoding = Base64.getEncoder().encodeToString("admin@algamoney.com:admin".getBytes("utf-8"));
		URL url = new URL("http://127.0.0.1:8080/mindropbox/0");
		URLConnection connection = url.openConnection();
		connection.setRequestProperty("Authorization", "Basic " + encoding);

		Arquivo arquivos = objectMapper.readValue(connection.getInputStream(), Arquivo.class);

		System.out.println(arquivos.getAuthor());

	}

	public static Resource getTestFile(UploadedFile uploadedFile) throws IOException {
		String extension = "." + uploadedFile.getFileName().substring(uploadedFile.getFileName().lastIndexOf(".") + 1);
		Path testFile = Files.createTempFile(uploadedFile.getFileName(), extension);
		System.out.println("Creating and Uploading Test File: " + testFile);
		Files.write(testFile, uploadedFile.getContents());
		return new FileSystemResource(testFile.toFile());
	}
	
	public static ByteArrayResource uploadToPost(UploadedFile uploadedFile) {
		ByteArrayResource contentsAsResource = new ByteArrayResource(uploadedFile.getContents()){
            @Override
            public String getFilename(){
                return uploadedFile.getFileName();
            }
        };
        
        return contentsAsResource;
	}
}
