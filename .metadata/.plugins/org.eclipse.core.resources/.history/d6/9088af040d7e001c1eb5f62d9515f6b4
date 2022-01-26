package com.example.usuario.controller;

import java.net.URI;
import com.example.usuario.error.ErrorUtils;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.web.bind.annotation.RestController;


import com.example.usuario.dto.UsuarioDTO;
import com.example.usuario.model.Usuario;
import com.example.usuario.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuariosService;
	
//	@Operation(summary = "Buscar usuario por ID", description = "Dado un ID, devuelve un objeto Usuario", tags= {"usuario"})
//	@ApiResponses(value = {
//			@ApiResponse(responseCode = "200", description = "Usuario localizado", content = {
//					@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
//			@ApiResponse(responseCode = "400", description = "No válido (NO implementado) ", content = @Content),
//			@ApiResponse(responseCode = "404", description = "Usuario no encontrado (NO implementado)", content = @Content) })
//
//	
//	@GetMapping("/{id}")
//	public UsuarioDTO getUsuario(@PathVariable Long id) {
//		log.info("--- usuario por id " + id);
//		final UsuarioDTO usuario = usuariosService.findById(id).orElseThrow();
//		return usuario;
//	}
	
	@Operation(summary = "Buscar todos los usuarios", description = "", tags= {"usuario"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuarios localizados", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
			@ApiResponse(responseCode = "400", description = "No válidos (NO implementados) ", content = @Content),
			@ApiResponse(responseCode = "404", description = "Usuarios no encontrados (NO implementados)", content = @Content) })
	@GetMapping
	public List<UsuarioDTO> getAllUsuarios(){
		log.info("--- todos los eventos");
		final List<UsuarioDTO> all = usuariosService.findAll();
		return all;

	}
	
	@Operation(summary = "Añade un nuevo Usuario", description = "Añade un usuario a la base de datos", tags= {"usuario"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Usuario añadido", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) })
			})
	@PostMapping
	public ResponseEntity<?> addUsuario(@Valid @RequestBody Usuario usuario){

		return new ResponseEntity<>(usuariosService.save(usuario), HttpStatus.CREATED);
	}

}
