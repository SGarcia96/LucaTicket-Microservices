package com.example.usuario.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuario.model.Entrada;
import com.example.usuario.service.EntradaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/entrada")
@Tag(name = "Entrada", description = "the Entrada API")
public class EntradaController {

	private static final Logger log = LoggerFactory.getLogger(EntradaController.class);

	@Autowired
	private EntradaService entradaService;

	@Operation(summary = "Buscar todas las entradas", description = "", tags = { "entrada" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Entradas localizados", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Entrada.class)) }),
			@ApiResponse(responseCode = "404", description = "Entradas no encontradas", content = @Content) })
	@GetMapping
	public List<Entrada> getAllEntrada() {
		log.info("--- todos los entradas");
		final List<Entrada> all = entradaService.findAll();
		return all;

	}

	@Operation(summary = "Añade una nueva Entrada", description = "Añade una entrada a la base de datos", tags = {
			"entrada" })
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", description = "Entrada añadida", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Entrada.class)) }),
			@ApiResponse(responseCode = "406", description = "Tarjeta caducada", content = @Content),
			@ApiResponse(responseCode = "406", description = "Datos incorrectos", content = @Content), 
			@ApiResponse(responseCode = "417", description = "Saldo insuficiente", content = @Content),
			@ApiResponse(responseCode = "451", description = "Aforo completo", content = @Content)}) 
	@PostMapping("/{id}/add")
	public ResponseEntity<?> addEntrada2(@PathVariable("id") Long id, @RequestParam String idEvento) {
		log.info("--- comprar entrada");
		Map<String, Object> body = entradaService.addEntrada(id, idEvento);
		return new ResponseEntity<>(body, (HttpStatus)body.get("status"));
	}

}
