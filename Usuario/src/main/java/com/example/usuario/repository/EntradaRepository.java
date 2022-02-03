package com.example.usuario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usuario.model.Entrada;
import com.example.usuario.model.EventoDTO;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Long> {
	
	public Optional<Entrada> findOneByEvento(EventoDTO evento);

	public List<Entrada> findAllByEventoNombre(String nombre);
	
}
