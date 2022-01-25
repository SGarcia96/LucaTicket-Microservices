package com.example.evento.service;

import java.util.List;
import com.example.evento.model.Evento;
import com.example.evento.model.EventoDTO;
import com.google.common.base.Optional;

public interface EventoService {
	
	public EventoDTO save(Evento evento);
	
	public List<EventoDTO> findAll();
	
	public Optional<EventoDTO> findById(String id);
}
