package com.example.evento.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evento.adapter.EventoAdapter;
import com.example.evento.model.Evento;
import com.example.evento.model.EventoDTO;
import com.example.evento.service.EventoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/evento")
@Tag(name = "evento", description = "The Evento API")
public class EventoController {

	private static final Logger log = LoggerFactory.getLogger(EventoController.class);
	
	@Autowired

	private EventoService eventoService;
	
	@Autowired
	private EventoAdapter eventoAdapter;
	
	@Operation(summary = "Buscar eventos por ID", description = "Dado un ID, devuelve un objeto Evento", tags= {"evento"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Evento localizado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class)) }),
			@ApiResponse(responseCode = "400", description = "No válido (NO implementado) ", content = @Content),
			@ApiResponse(responseCode = "404", description = "Evento no encontrado (NO implementado)", content = @Content) })
	@GetMapping("/{id}")
	public EventoDTO getEvento(@PathVariable Long id) {
		log.info("--- evento por id " + id);
		final Optional<Evento> evento = eventoService.findId(id).orElseThrow();
		return eventoAdapter.of(evento.orElseThrow());
	}
	
	@Operation(summary = "Buscar todos los eventos", description = "", tags= {"evento"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Eventos localizados", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class)) }),
			@ApiResponse(responseCode = "400", description = "No válidos (NO implementados) ", content = @Content),
			@ApiResponse(responseCode = "404", description = "Eventos no encontrados (NO implementados)", content = @Content) })
	@GetMapping("/eventos")
	public List<EventoDTO> getAllEventos(){
		log.info("--- todos los eventos");
		final List<Evento> all = eventoService.findAll();
		return eventoAdapter.of(all);
	}
	
}
