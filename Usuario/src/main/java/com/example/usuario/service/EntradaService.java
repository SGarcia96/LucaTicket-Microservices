package com.example.usuario.service;

import com.example.usuario.dto.UsuarioDTO;

public interface EntradaService {
	
	public Entrada addEntrada(long idUsuario, String idEvento, boolean vip);

}
