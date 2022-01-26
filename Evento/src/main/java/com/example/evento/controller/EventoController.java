package com.example.evento.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evento.model.Evento;
import com.example.evento.model.EventoDTO;
import com.example.evento.service.EventoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Validated
@RequestMapping("/eventos")
@Tag(name = "evento", description = "Evento API")
public class EventoController {

	private static final Logger log = LoggerFactory.getLogger(EventoController.class);

	@Autowired
	private EventoService eventoService;

	@Operation(summary = "Buscar todos los eventos", description = "devuelve todos los eventos registrados", tags= {"evento"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Eventos localizados", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class)) }),
			@ApiResponse(responseCode = "400", description = "No válidos (NO implementados) ", content = @Content),
			@ApiResponse(responseCode = "404", description = "Eventos no encontrados (NO implementados)", content = @Content) })
	@GetMapping
	public List<EventoDTO> getAllEventos() {
		log.info("--- todos los eventos");
		final List<EventoDTO> all = eventoService.findAll();
		return all;
	}

	@Operation(summary = "Buscar eventos por ID", description = "Dado un ID, devuelve un objeto Evento", tags = {
			"evento" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Evento localizado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class)) }),
			@ApiResponse(responseCode = "404", description = "Evento no encontrado (ID no existe)", content = @Content) })
	@GetMapping("/{id}")
	public EventoDTO getEvento(
			@Parameter(description = "ID del evento a localizar", required = true) @PathVariable("id") String id) {
		log.info("--- evento por id " + id);
		final EventoDTO evento = eventoService.findById(id);
		return evento;
	}

	@Operation(summary = "Añade un Evento", description = "Añade un evento a la coleccion eventos", tags = { "evento" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Evento añadido", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class)) }),
			@ApiResponse(responseCode = "400", description = "BAD_REQUEST, algún campo no es correcto", content = @Content) })
	@PostMapping
	public ResponseEntity<EventoDTO> addEvento(@Valid @RequestBody Evento evento) {
		EventoDTO newEvento = eventoService.save(evento);
		return new ResponseEntity<>(newEvento, HttpStatus.CREATED);
	}

	@Operation(summary = "Eliminar un evento por ID", description = "Dado un ID, elimina el evento", tags = {
			"evento" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Evento eliminado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class)) }),
			@ApiResponse(responseCode = "404", description = "Evento no encontrado (ID no existe)", content = @Content) })
	@DeleteMapping("/{id}")
	public void deleteEvento(
			@Parameter(description = "ID del evento a localizar", required = true) @PathVariable("id") String id) {
		log.info("--- deleteEvento con id " + id);
		eventoService.deleteById(id);
	}

	@Operation(summary = "Buscar eventos por género", description = "Dado un género, devuelve todos los eventos de dicho género", tags = {
			"evento" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Eventos localizados", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class)) }),
			@ApiResponse(responseCode = "400", description = "No válidos (NO implementados) ", content = @Content),
			@ApiResponse(responseCode = "404", description = "Eventos no encontrados (NO implementados)", content = @Content) })
	@GetMapping("findAllByGenero/{genero}")
	public List<EventoDTO> getAllEventosByGenero(
			@Parameter(description = "Género del evento a localizar", required = true) @PathVariable("genero") String genero) {
		log.info("--- eventos por genero " + genero);
		final List<EventoDTO> eventos = eventoService.findAllByGenero(genero);
		return eventos;
	}

	@Operation(summary = "Buscar eventos por nombre", description = "Dado un nombre, devuelve uno o varios objetos Evento", tags= {"evento"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Eventos localizados", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class)) }),
			@ApiResponse(responseCode = "400", description = "No válido (NO implementado) ", content = @Content),
			@ApiResponse(responseCode = "404", description = "Evento no encontrado (NO implementado)", content = @Content) })
	@GetMapping("findAllByNombre/{nombre}")
	public List<EventoDTO> findAllByNombre(
			@Parameter(description = "Nombre del evento a localizar", required=true)
			@PathVariable("nombre") String nombre) {
		log.info("--- eventos por nombre " + nombre);
		final List<EventoDTO> eventos = eventoService.findAllByNombre(nombre);
		return eventos;
	}

}
