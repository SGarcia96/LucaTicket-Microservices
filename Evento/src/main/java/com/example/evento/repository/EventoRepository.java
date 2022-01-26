package com.example.evento.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import com.example.evento.model.Evento;

@Repository
public interface EventoRepository extends MongoRepository<Evento, String> {

}
