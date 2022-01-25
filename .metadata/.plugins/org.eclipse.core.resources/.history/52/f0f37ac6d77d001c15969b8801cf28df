package com.example.evento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.evento.adapter.EventoAdapter;
import com.example.evento.model.Evento;
import com.example.evento.model.EventoDTO;
import com.example.evento.repository.EventoRepository;

public class EventoServiceImpl implements EventoService {

	@Autowired
	private EventoRepository eventoRepository;

	@Override
	public EventoDTO save(Evento evento) {
		return EventoAdapter.of(eventoRepository.save(evento));
	}

	@Override
	public List<EventoDTO> findAll() {
		return EventoAdapter.of(eventoRepository.findAll());
	}

	@Override
	public EventoDTO findById(String id) {
		return EventoAdapter.of(eventoRepository.findById(id).orElseThrow());
	}
}
