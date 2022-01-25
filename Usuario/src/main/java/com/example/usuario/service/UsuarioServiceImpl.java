package com.example.usuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usuario.adapter.UsuarioAdapter;
import com.example.usuario.dto.UsuarioDTO;
import com.example.usuario.model.Usuario;
import com.example.usuario.repository.UsuarioRepository;


@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioAdapter usuarioAdapter;
	
	@Override
	public UsuarioDTO save(Usuario usuario) {
		return usuarioAdapter.of(usuarioRepository.save(usuario));
	}
		
	
	@Override
	public List<UsuarioDTO> findAll() {
		return usuarioAdapter.of(usuarioRepository.findAll());
	}

}
