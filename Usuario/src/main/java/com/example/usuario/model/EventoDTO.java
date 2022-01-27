package com.example.usuario.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nombre;
	private int aforo;
	private float[] rangoPrecios;
	/*
	public static EventoDTO of(EntradaEvento evento) {
		EventoDTO event = new EventoDTO();
		event.setId(evento.getId());
		event.setNombre(evento.getNombre());
		event.setAforo(evento.getAforo());
		event.setRangoPrecios(evento.getRangoPrecios());

		return event;
	}
	*/
}
