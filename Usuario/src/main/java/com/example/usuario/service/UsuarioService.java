package com.example.usuario.service;

import java.util.List;

import com.example.usuario.model.Usuario;

public interface UsuarioService {

	public Usuario save(Usuario usuario);
	
	public List<Usuario> findAll();
}
