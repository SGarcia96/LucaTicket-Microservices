package com.example.usuario.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.usuario.adapter.UsuarioAdapter;
import com.example.usuario.controller.error.UsuarioNotFoundException;
import com.example.usuario.dto.UsuarioDTO;
import com.example.usuario.model.Usuario;
import com.example.usuario.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioAdapter usuarioAdapter;

	@Override
	public UsuarioDTO save(Usuario usuario) {
		String password = usuario.getPassword();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		
		usuario.setPassword(hashedPassword);
		usuario.setFechaAlta(LocalDate.now(ZoneId.of("Europe/Madrid")));
		return usuarioAdapter.of(usuarioRepository.save(usuario));
	}

	@Override
	public List<UsuarioDTO> findAll() {
		final List<UsuarioDTO> lista = usuarioAdapter.of(usuarioRepository.findAll());
		if (lista.isEmpty()) {
			throw new UsuarioNotFoundException("No se encontró ningún usuario");
		}
		return lista;
	}

	@Override
	public UsuarioDTO findByMail(String mail) {
		return usuarioAdapter.of(usuarioRepository.findByMail(mail).orElseThrow(UsuarioNotFoundException::new));
	}
	

	@Override
	public String getJWTToken(UsuarioDTO usuario) {
		String secretKey = "grupo3";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(usuario.getMail())
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000000))
				//se define la clave de encriptación y el algoritmo para generar el hash
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
	
	@Override
	public UsuarioDTO findById(Long id) {
		return usuarioAdapter.of(usuarioRepository.findById(id).orElseThrow(UsuarioNotFoundException::new));
	}

	@Override
	public void deleteById(Long id) {
		if (!usuarioRepository.findById(id).isPresent()) throw new UsuarioNotFoundException();
		usuarioRepository.deleteById(id);
	}
	public UsuarioDTO update(Usuario usuario) {
		Usuario newUsuario = usuarioRepository.findById(usuario.getId()).orElseThrow(UsuarioNotFoundException::new);
		newUsuario.setNombre(usuario.getNombre());
		newUsuario.setApellido(usuario.getApellido());
		newUsuario.setMail(usuario.getMail());
		newUsuario.setPassword(usuario.getPassword());
		newUsuario.setFechaAlta(usuario.getFechaAlta());
		return this.save(newUsuario);
	}

	public UsuarioDTO update(Long id,Usuario usuario) {
		Usuario newUsuario = usuarioRepository.findById(id).orElseThrow(UsuarioNotFoundException::new);
		newUsuario.setNombre(usuario.getNombre());
		newUsuario.setApellido(usuario.getApellido());
		newUsuario.setMail(usuario.getMail());
		newUsuario.setPassword(usuario.getPassword());
		newUsuario.setFechaAlta(usuario.getFechaAlta());
		return this.save(newUsuario);
	}

}
