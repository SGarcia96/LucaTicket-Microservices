package com.example.evento.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.evento.model.Evento;
import com.example.evento.model.EventoDTO;

@Component
public class EventoAdapter {
	
	public static EventoDTO of(Evento evento) {
		EventoDTO eventoDTO= new EventoDTO();
		eventoDTO.setId(evento.getId());
		eventoDTO.setNombre(evento.getNombre());
		eventoDTO.setDescripcionCorta(evento.getDescripcionCorta());
		eventoDTO.setDescripcionLarga(evento.getDescripcionLarga());
		eventoDTO.setFotoUrl(evento.getFotoUrl());
		eventoDTO.setFechaEvento(evento.getFechaEvento());
		eventoDTO.setHoraEvento(evento.getHoraEvento());
		eventoDTO.setRangoPrecios(evento.getRangoPrecios());
		eventoDTO.setPoliticaAcceso(evento.getPoliticaAcceso());
		eventoDTO.setRecinto(evento.getRecinto());
		return eventoDTO;
	}
	
	public static List<EventoDTO> of(List<Evento> eventos){
		return eventos.stream()
				.map(p -> of(p))
				.collect(Collectors.toList());
	}
}
