package com.example.usuario.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//ERROR GENERICO DE LA GAMA DEL 207
//No consigo cualificar los datos
@ResponseStatus(HttpStatus.MULTI_STATUS)
public class UsuarioDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioDataException(String mensaje) {
		super(mensaje);
	}

}
