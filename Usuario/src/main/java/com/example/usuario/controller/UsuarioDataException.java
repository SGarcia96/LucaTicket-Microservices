package com.example.usuario.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.MULTI_STATUS)
public class UsuarioDataException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public UsuarioDataException(String mensaje) {
		super(mensaje);
	}
	public void action1() {
		
	}

}
