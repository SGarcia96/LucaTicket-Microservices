package com.example.usuario.controller.error;

public class UsuarioNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioNotFoundException() {
		super("Epic Fail: No existe el usuario");
	}
	
	public UsuarioNotFoundException(String mensaje) {
		super(mensaje);
	}

	public UsuarioNotFoundException(Long id) {
		super("Epic Fail: No existe el usuario " + id);
	}

}
