package com.example.usuario.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuario.controller.error.IncorrectPasswordException;
import com.example.usuario.dto.UsuarioDTO;
import com.example.usuario.model.Usuario;
import com.example.usuario.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuario", description = "the Usuario API")
public class UsuarioController {

	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioService usuariosService;

	@Operation(summary = "Buscar usuario por ID", description = "Dado un ID, devuelve un objeto Usuario", tags= {"usuario"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuario localizado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
			@ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content) })
	@GetMapping("/{id}")
	public UsuarioDTO getUsuario(
			@Parameter(description = "ID del usuario a localizar", required = true) @PathVariable("id") Long id) {
		log.info("--- usuario por id " + id);
		final UsuarioDTO usuario = usuariosService.findById(id);
		return usuario;
	}

	@Operation(summary = "Buscar todos los usuarios", description = "", tags = { "usuario" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuarios localizados", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
			@ApiResponse(responseCode = "404", description = "Usuarios no encontrados", content = @Content) })
	@GetMapping
	public List<UsuarioDTO> getAllUsuarios() {
		log.info("--- todos los eventos");
		final List<UsuarioDTO> all = usuariosService.findAll();
		return all;

	}

	@Operation(summary = "Añade un nuevo Usuario", description = "Añade un usuario a la base de datos", tags = {
			"usuario" })
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Usuario añadido", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }), 
	@ApiResponse(responseCode = "400", description = "Valores incorrectos", content = @Content)})
	@PostMapping
	public ResponseEntity<?> addUsuario(@Valid @RequestBody Usuario usuario) {
		log.info("--- add usuario: " + usuario);
		return new ResponseEntity<>(usuariosService.save(usuario), HttpStatus.CREATED);
	}

	@Operation(summary = "Editar un Usuario", description = "Dado el ID de un usuario y sus campos modificados, actualiza el usuario",
			tags = { "evento" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuario modificado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
			@ApiResponse(responseCode = "400", description = "BAD_REQUEST, algún campo no es correcto", content = @Content),
			@ApiResponse(responseCode = "404", description = "Usuario no encontrado (ID no existe)", content = @Content) })
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> updateEvento(@PathVariable("id") Long id, @Valid @RequestBody Usuario usuario) {
		
		UsuarioDTO newUsuario = usuariosService.update(id,usuario);
		return new ResponseEntity<>(newUsuario, HttpStatus.OK);
	}

	@Operation(summary = "Login", description = "Permite a un usuario loguearse", tags = { "usuario" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuario y contraseña correctos", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
			@ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content),
			@ApiResponse(responseCode = "400", description = "Contraseña incorrecta ", content = @Content) })
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam("usuario") String usuario, @RequestParam("password") String pwd) {

		UsuarioDTO usuarioRegistrado = usuariosService.findByMail(usuario);

		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		boolean isPasswordMatches = bcrypt.matches(pwd, usuarioRegistrado.getPassword());

		if (!isPasswordMatches) {
			throw new IncorrectPasswordException();
		}
		String token = usuariosService.getJWTToken(usuarioRegistrado);
		return new ResponseEntity<>(token, HttpStatus.OK);
	}

	@Operation(summary = "Eliminar un usuario por ID", description = "Dado un ID, elimina el usuario", tags = {
			"usuario" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuario eliminado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
			@ApiResponse(responseCode = "404", description = "Usuario no encontrado (ID no existe)", content = @Content) })
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String deleteEvento(
			@Parameter(description = "ID del usuario a localizar", required = true) @PathVariable("id") Long id) {
		log.info("--- deleteEvento con id " + id);
		usuariosService.deleteById(id);
		
		return "{\"message\": \"se ha eliminado el usuario con id: " + id + "\"" + "}";
	}

}
