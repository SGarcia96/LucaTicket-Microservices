package com.example.evento.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//NOT FOUND: 404
@ResponseStatus(HttpStatus.NOT_FOUND)
class EventoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EventoNotFoundException() {
		super("Epic Fail: No existe el evento");

	}

	public EventoNotFoundException(Long id) {
		super("Epic Fail: No existe el evento" + id);
	}

}
