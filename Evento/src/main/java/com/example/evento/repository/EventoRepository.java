package com.example.evento.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.evento.model.Evento;
import com.example.evento.model.EventoDTO;

@Repository
public interface EventoRepository extends MongoRepository<Evento, String> {

	List<Evento> findAllByGenero(String genero);

	public List<Evento> findByNombre(String nombre);

}
