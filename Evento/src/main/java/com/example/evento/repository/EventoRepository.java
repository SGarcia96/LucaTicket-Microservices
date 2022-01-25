package com.example.evento.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.evento.model.Evento;

public interface EventoRepository extends MongoRepository<Evento, String> {

}
