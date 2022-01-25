package com.example.evento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.evento.model.Evento;
import com.example.evento.model.EventoDTO;
import com.example.evento.repository.EventoRepository;
import com.google.common.base.Optional;

public class EventoServiceImpl implements EventoService {

	@Autowired
	private EventoRepository eventoRepository;

	@Override
	public EventoDTO save(Evento evento) {
		return eventoRepository.save(evento);
	}

	@Override
	public List<EventoDTO> findAll() {
		return eventoRepository.findAll();
	}

	@Override
	public Optional<EventoDTO> findById(String id) {
		return eventoRepository.findById(id);
	}
}
