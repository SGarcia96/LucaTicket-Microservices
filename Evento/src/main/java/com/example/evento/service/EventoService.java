package com.example.evento.service;

import java.util.List;
import com.example.evento.model.Evento;
import com.example.evento.model.EventoDTO;

public interface EventoService {
	
	public EventoDTO save(Evento evento);
	
	public List<EventoDTO> findAll();
	
	public EventoDTO findById(String id);
	
	public List<EventoDTO> findAllByNombre(String nombre);
}
