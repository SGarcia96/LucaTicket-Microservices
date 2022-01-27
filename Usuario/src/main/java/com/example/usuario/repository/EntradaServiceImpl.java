package com.example.usuario.repository;

import org.springframework.stereotype.Repository;

import com.example.usuario.dto.UsuarioDTO;
import com.example.usuario.service.EntradaService;

@Repository
public class EntradaServiceImpl implements EntradaService{

	@Override
	public Entrada addEntrada(long idUsuario, String idEvento, boolean vip) {
		
		return null;
	}

}
