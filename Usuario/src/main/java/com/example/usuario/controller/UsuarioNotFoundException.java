package com.example.usuario.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//NOT FOUND: 404
@ResponseStatus(HttpStatus.NOT_FOUND)
class UsuarioNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public UsuarioNotFoundException() {
		super("Epic Fail: No existe el usuario");
		
	}
	public UsuarioNotFoundException(Long id) {
		super("Epic Fail: No existe el usuario"+id);
	}
	

}
