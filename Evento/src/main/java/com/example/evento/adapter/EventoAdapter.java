package com.example.evento.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.evento.model.Evento;
import com.example.evento.model.EventoDTO;
import com.example.evento.utils.RandomNumber;

@Component
public class EventoAdapter {
	
	public static EventoDTO of(Evento evento) {
		EventoDTO eventoDTO= new EventoDTO();
		eventoDTO.setId(evento.getId());
		eventoDTO.setNombre(evento.getNombre());
		eventoDTO.setPrecio(RandomNumber.creaFloatRandom(evento.getRangoPrecios()[0], evento.getRangoPrecios()[1]));
		eventoDTO.setAforo(evento.getRecinto().getAforo());
		return eventoDTO;
	}
	
	public static List<EventoDTO> of(List<Evento> eventos){
		return eventos.stream()
				.map(p -> of(p))
				.collect(Collectors.toList());
	}

}
