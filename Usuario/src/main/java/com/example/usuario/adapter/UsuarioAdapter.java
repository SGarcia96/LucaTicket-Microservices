package com.example.usuario.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.usuario.dto.UsuarioDTO;
import com.example.usuario.model.Usuario;

@Component
public class UsuarioAdapter {

	public UsuarioDTO of(Usuario usuario) {
		UsuarioDTO usuarioDto = new UsuarioDTO();
		usuarioDto.setId(usuario.getId());
		usuarioDto.setNombre(usuario.getNombre());
		usuarioDto.setApellido(usuario.getApellido());
		usuarioDto.setMail(usuario.getMail());
		usuarioDto.setPassword(usuario.getPassword());
		usuarioDto.setFechaAlta(usuario.getFechaAlta());
		return usuarioDto;
	}
	
	public List<UsuarioDTO> of(List<Usuario> usuarios){
		return usuarios
				.stream()
				.map(u -> of(u))
				.collect(Collectors.toList());
	}
	
}
