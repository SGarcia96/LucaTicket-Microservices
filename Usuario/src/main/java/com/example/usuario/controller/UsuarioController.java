package com.example.usuario.controller;

import java.net.URI;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.usuario.model.Usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController<UsuarioService, UsuarioAdapter> {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuariosService;
	
	@Autowired
	private UsuarioAdapter usuariosAdapter;
	
	@Operation(summary = "Buscar usuario por ID", description = "Dado un ID, devuelve un objeto Usuario", tags= {"usuario"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuario localizado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
			@ApiResponse(responseCode = "400", description = "No válido (NO implementado) ", content = @Content),
			@ApiResponse(responseCode = "404", description = "Usuario no encontrado (NO implementado)", content = @Content) })

	
	@GetMapping("/{id}")
	public UsuarioResponse getUsuario(@PathVariable Long id) {
		log.info("--- usuario por id " + id);
		final Usuario usuario = usuarioService.findId(id).orElseThrow();
		return UsuarioAdapter.of(usuario);
	}
	
	@GetMapping("/usuarios")
	public String findAllUsuarios(Model m) {
		m.addAttribute("usuarios", usuarioService.findAllUsuarios());
		log.info(usuarioService.findAllUsuarios().toString());
		log.info("en el metodo findAllUsuarios del controller");
		return "listaUsuarios";

	}

}
