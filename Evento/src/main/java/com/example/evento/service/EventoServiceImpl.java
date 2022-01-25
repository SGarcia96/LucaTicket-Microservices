package com.example.evento.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.evento.model.Evento;
import com.example.evento.repository.EventoRepository;

public class EventoServiceImpl implements EventoService {

	@Autowired
	private EventoRepository eventoRepository;

	@Override
	public Evento save(Evento evento) {
		return eventoRepository.save(evento);
	}

}
