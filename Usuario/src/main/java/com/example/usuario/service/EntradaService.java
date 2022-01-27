package com.example.usuario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.usuario.dto.UsuarioDTO;
import com.example.usuario.model.Entrada;

@Service
public interface EntradaService {
	
	public Entrada addEntrada(Entrada entrada);
	
	public List<Entrada> findAll();

}
