package com.example.evento.service;

import java.util.List;
import com.example.evento.model.Evento;

public interface EventoService {
	
	public Evento save(Evento evento);
	public List<Evento> findAll();
}
