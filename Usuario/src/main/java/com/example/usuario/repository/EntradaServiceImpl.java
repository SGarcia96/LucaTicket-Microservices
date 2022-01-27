package com.example.usuario.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.usuario.adapter.UsuarioAdapter;
import com.example.usuario.dto.UsuarioDTO;
import com.example.usuario.model.Entrada;
import com.example.usuario.service.EntradaService;

@Repository
public class EntradaServiceImpl implements EntradaService{
	
	@Autowired
	private EntradaRepository entradaRepository;
	

	@Override
	public Entrada addEntrada(Entrada entrada) {
		
		return entradaRepository.save(entrada);
	}
	
	@Override
	public List<Entrada> findAll(){
		return entradaRepository.findAll();
	}

}
