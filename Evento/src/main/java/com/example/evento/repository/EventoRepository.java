package com.example.evento.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.evento.model.Evento;

@Repository
public interface EventoRepository extends MongoRepository<Evento, String> {
	
	public List<Evento> findAll();
		
	
}
