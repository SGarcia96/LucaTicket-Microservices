package com.example.usuario.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.usuario.model.Entrada;
import com.example.usuario.model.EventoDTO;

@Service
public interface EntradaService {

	public List<Entrada> findAll();

	public Map<String, Object> addEntrada(Long idUsuario, String idEvento);

	public Entrada saveEntrada(Long idUsuario, EventoDTO evento);

}
