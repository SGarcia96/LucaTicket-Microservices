package com.example.evento.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evento.model.Evento;
import com.example.evento.service.EventoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/eventos")
@Tag(name = "evento", description = "Evento API")
public class EventoController {

	private static final Logger log = LoggerFactory.getLogger(EventoController.class);
	
	@Autowired
	private EventoService eventoService;
	
	@Autowired
	private EventoAdapter eventoAdapter;
	
	@GetMapping("/{id}")
	public EventoResponse getEvento(@PathVariable Long id) {
		log.info("--- evento por id " + id);
		final Evento evento = eventoRepository.findId(id).orElseThrow();
		return eventoAdapter.of(evento);
	}
	
	@Operation(summary = "Buscar estudiantes por ID", description = "Dado un ID, devuelve un objeto Student", tags= {"student"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Estudiante añadido", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class)) })
			})
	@PostMapping
	public ResponseEntity<Evento> addEvento(@Valid @RequestBody Evento evento) {
		
		return new ResponseEntity<>(eventoService.save(evento), HttpStatus.CREATED);
	}
	
}
