package com.example.usuario.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.usuario.dto.UsuarioDTO;
import com.example.usuario.model.Usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController<UsuarioService, UsuarioAdapter> {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioAdapter usuarioAdapter;
	
	@GetMapping("/{id}")
	public UsuarioDTO getUsuario(@PathVariable Long id) {
		log.info("--- usuario por id " + id);
		final Usuario usuario = usuarioService.findById(id).orElseThrow();
		return UsuarioAdapter.of(usuario);
	}

	@Operation(summary = "Buscar usuario por ID", description = "Dado un ID, devuelve un objeto Usuario", tags= {"usuario"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuario localizado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
			@ApiResponse(responseCode = "400", description = "No válido (NO implementado) ", content = @Content),
			@ApiResponse(responseCode = "404", description = "Usuario no encontrado (NO implementado)", content = @Content) })
	@GetMapping("/{id}")
	public Usuario readUsuario(
			@Parameter(description = "ID del usuario a localizar", required=true) 
			@PathVariable Long id) {
		System.out.println("-------- readUsuario ");
		return serv.findById(id).orElseThrow(UsuarioNotFoundException::new);
	}

	// @RequestBody Student student significa que un usuario será el cuerpo de la
	// respuesta
	// Devuelve en la cabecera la URL
	@PostMapping
	public ResponseEntity<?> addUsuario(@RequestBody Usuario usuario) {
		Usuario result = this.serv.save(usuario);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId())
				.toUri();
}
}
