package com.example.evento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evento.adapter.EventoAdapter;
import com.example.evento.controller.error.EventoNotFoundException;
import com.example.evento.model.Evento;
import com.example.evento.model.EventoDTO;
import com.example.evento.repository.EventoRepository;

@Service
public class EventoServiceImpl implements EventoService {

	@Autowired
	private EventoRepository eventoRepository;

	@Override
	public Evento save(Evento evento) {
		return eventoRepository.save(evento);
	}

	@Override
	public List<Evento> findAll() {
		return eventoRepository.findAll();
	}

	@Override
	public Evento findById(String id) {
		return eventoRepository.findById(id).orElseThrow(EventoNotFoundException::new);
	}

	public Evento update(Evento evento) {
		Evento newEvento = eventoRepository.findById(evento.getId()).orElseThrow(EventoNotFoundException::new);
		newEvento.setNombre(evento.getNombre());
		newEvento.setDescripcionCorta(evento.getDescripcionCorta());
		newEvento.setDescripcionLarga(evento.getDescripcionLarga());
		newEvento.setFechaEvento(evento.getFechaEvento());
		newEvento.setFotoUrl(evento.getFotoUrl());
		newEvento.setHoraEvento(evento.getHoraEvento());
		newEvento.setPoliticaAcceso(evento.getPoliticaAcceso());
		newEvento.setId(evento.getId());
		newEvento.setRangoPrecios(evento.getRangoPrecios());
		newEvento.setRecinto(evento.getRecinto());

		return this.save(newEvento);
	}

	@Override
	public Evento update(String id, Evento evento) {

		Evento newEvento = eventoRepository.findById(id).orElseThrow(EventoNotFoundException::new);
		newEvento.setNombre(evento.getNombre());
		newEvento.setDescripcionCorta(evento.getDescripcionCorta());
		newEvento.setDescripcionLarga(evento.getDescripcionLarga());
		newEvento.setFechaEvento(evento.getFechaEvento());
		newEvento.setFotoUrl(evento.getFotoUrl());
		newEvento.setHoraEvento(evento.getHoraEvento());
		newEvento.setPoliticaAcceso(evento.getPoliticaAcceso());
		newEvento.setGenero(evento.getGenero());
		newEvento.setRangoPrecios(evento.getRangoPrecios());
		newEvento.setRecinto(evento.getRecinto());

		return this.save(newEvento);
	}

	@Override
	public void deleteById(String id) {
		eventoRepository.deleteById(id);
	}

	@Override
	public List<Evento> findAllByGenero(String genero) {
		return eventoRepository.findAllByGenero(genero);
	}

	@Override
	public List<Evento> findAllByNombre(String nombre) {
		return eventoRepository.findByNombre(nombre);
	}
	
	@Override
	public List<Evento> findAllByLugar(String lugar) {
		return eventoRepository.findAllByRecintoLugar(lugar);
	}
}
