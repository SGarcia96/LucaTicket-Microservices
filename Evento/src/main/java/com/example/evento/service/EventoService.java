package com.example.evento.service;

import java.util.List;
import com.example.evento.model.Evento;

public interface EventoService {
	
	public Evento save(Evento evento);
	
	public List<Evento> findAll();
	
	public Evento findById(String id);

	public Evento update(String id, Evento evento);

	public void deleteById(String id);
	
	public List<Evento> findAllByGenero(String genero);

	public List<Evento> findAllByNombre(String nombre);
	
	public List<Evento> findAllByLugar(String lugar);

}
