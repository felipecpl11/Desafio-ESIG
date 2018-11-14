package com.minidrobox.api.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encoder {
	public static void main (String args[]) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaCodificada = encoder.encode("admin");
		System.out.println(senhaCodificada);
		
		if(encoder.matches("adminn", "$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5."))
			System.out.println("SHOW");
		else
			System.out.println("ERRO");
	}
}
