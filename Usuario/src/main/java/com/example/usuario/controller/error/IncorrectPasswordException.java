package com.example.usuario.controller.error;

public class IncorrectPasswordException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public IncorrectPasswordException() {
		super("Epic Fail: Contraseña incorrecta");	
	}
	
}
