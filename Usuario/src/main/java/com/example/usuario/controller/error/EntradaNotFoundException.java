package com.example.usuario.controller.error;

public class EntradaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntradaNotFoundException() {
		super("Epic Fail: No existe la entrada");
	}
	
	public EntradaNotFoundException(String mensaje) {
		super(mensaje);
	}

	public EntradaNotFoundException(Long id) {
		super("Epic Fail: No existe la entrada " + id);
	}

}
