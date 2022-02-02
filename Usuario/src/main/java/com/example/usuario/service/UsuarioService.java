package com.example.usuario.service;

import java.util.List;

import com.example.usuario.dto.UsuarioDTO;
import com.example.usuario.model.Usuario;

public interface UsuarioService {

	public UsuarioDTO save(Usuario usuario);

	public List<UsuarioDTO> findAll();

	public UsuarioDTO findByMail(String mail);

	public String getJWTToken(UsuarioDTO usuario);
	
	public void deleteById(Long id);

}
