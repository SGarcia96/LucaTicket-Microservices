package com.example.evento.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evento.adapter.EventoAdapter;
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

	@Operation(summary = "Buscar todos los eventos", description = "devuelve todos los eventos registrados", tags = {
			"evento" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Eventos localizados", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class)) }),
			@ApiResponse(responseCode = "404", description = "La lista de eventos se encuentra vacia", content = @Content) })
	@GetMapping
	public List<Evento> getAllEventos() {
		log.info("--- todos los eventos");
		final List<Evento> all = eventoService.findAll();
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
		final EventoDTO evento = EventoAdapter.of(eventoService.findById(id));
		return evento;
	}

	@Operation(summary = "Buscar eventos por género", description = "Dado un género, devuelve todos los eventos de dicho género", tags = {
			"evento" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Eventos localizados", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class)) }),
			@ApiResponse(responseCode = "404", description = "Eventos de ese género no encontrados)", content = @Content) })
	@GetMapping("findAllByGenero/{genero}")
	public List<Evento> getAllEventosByGenero(
			@Parameter(description = "Género del evento a localizar", required = true) @PathVariable("genero") String genero) {
		log.info("--- eventos por genero " + genero);
		final List<Evento> eventos = eventoService.findAllByGenero(genero);
		return eventos;
	}

	@Operation(summary = "Buscar eventos por nombre", description = "Dado un nombre, devuelve uno o varios objetos Evento", tags = {
			"evento" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Eventos localizados", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class)) }),
			@ApiResponse(responseCode = "404", description = "Evento no encontrado con ese nombre", content = @Content) })
	@GetMapping("findAllByNombre/{nombre}")
	public List<Evento> findAllByNombre(
			@Parameter(description = "Nombre del evento a localizar", required = true) @PathVariable("nombre") String nombre) {
		log.info("--- eventos por nombre " + nombre);
		final List<Evento> eventos = eventoService.findAllByNombre(nombre);
		return eventos;
	}

	@Operation(summary = "Añade un Evento", description = "Añade un evento a la coleccion eventos", tags = { "evento" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Evento añadido", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class)) }),
			@ApiResponse(responseCode = "400", description = "BAD_REQUEST, algún campo no es correcto", content = @Content) })
	@PostMapping
	public ResponseEntity<Evento> addEvento(@Valid @RequestBody Evento evento) {
		Evento newEvento = eventoService.save(evento);
		return new ResponseEntity<>(newEvento, HttpStatus.CREATED);
	}

	@Operation(summary = "Editar un Evento", description = "Dado el ID de un evento y sus campos modificados, actualiza el evento", tags = {
			"evento" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Evento modificado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class)) }),
			@ApiResponse(responseCode = "400", description = "BAD_REQUEST, algún campo no es correcto", content = @Content),
			@ApiResponse(responseCode = "404", description = "Evento no encontrado (ID no existe)", content = @Content) })
	@PutMapping("/{id}")
	public ResponseEntity<Evento> updateEvento(@PathVariable("id") String id, @Valid @RequestBody Evento evento) {
		Evento newEvento = eventoService.update(id, evento);
		return new ResponseEntity<>(newEvento, HttpStatus.OK);
	}

	@Operation(summary = "Eliminar un evento por ID", description = "Dado un ID, elimina el evento", tags = {
			"evento" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Evento eliminado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class)) }),
			@ApiResponse(responseCode = "404", description = "Evento no encontrado (ID no existe)", content = @Content) })
	@DeleteMapping("/{id}")
	public Map<String, Object> deleteEvento(
			@Parameter(description = "ID del evento a localizar", required = true) @PathVariable("id") String id) {
		log.info("--- deleteEvento con id " + id);
		eventoService.deleteById(id);
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
		body.put("message", "Evento eliminado con éxito");
		body.put("status", 200);
		return body;
	}

	@Operation(summary = "Buscar eventos por ciudad", description = "Dado una ciudad, devuelve uno o varios objetos Evento", tags = {
			"evento" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Evento encontrado en la ciudad", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class)) }),
			@ApiResponse(responseCode = "400", description = "No válido (NO implementado) ", content = @Content),
			@ApiResponse(responseCode = "404", description = "Evento no encontrado en la ciudad (NO implementado)", content = @Content) })
	@GetMapping("findAllByCiudad/{ciudad}")
	public List<Evento> findAllByLugar(
			@Parameter(description = "Nombre de la ciudad donde buscar evento", required = true) @PathVariable("ciudad") String lugar) {
		log.info("--- eventos por ciudad " + lugar);
		final List<Evento> eventos = eventoService.findAllByLugar(lugar);
		return eventos;
	}

}
