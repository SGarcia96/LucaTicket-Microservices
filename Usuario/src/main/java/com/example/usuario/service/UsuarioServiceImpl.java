package com.example.usuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usuario.model.Usuario;
import com.example.usuario.repository.UsuarioRepository;


@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
		
	
	@Override
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
		}
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
