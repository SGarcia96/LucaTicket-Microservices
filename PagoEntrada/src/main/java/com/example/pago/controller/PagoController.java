package com.example.pago.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pago.model.MensajePago;
import com.example.pago.service.PagoService;
import com.example.pago.utils.RandomTarjeta;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pago")
@Tag(name = "pago", description = "Pago API")
public class PagoController {

	private static final Logger log = LoggerFactory.getLogger(PagoController.class);

	@Autowired
	private PagoService pagoService;
	
/*	@Operation(summary = "Verificar pago", description = "Dados unos datos, devuelve una tarjeta", tags = {
	"pago" })
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Tarjeta guardada", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = MensajePago.class)) }),
		@ApiResponse(responseCode = "400", description = "BAD_REQUEST, algún campo no es correcto", content = @Content),
		@ApiResponse(responseCode = "404", description = "Pago no encontrado", content = @Content) })
	@PostMapping 
	public void ingresarTarjeta() {
		
	}*/

	@Operation(summary = "Verificar pago", description = "Dada una entrada comprada, devuelve información del pago", tags = {
			"pago" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pago correcto", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = MensajePago.class)) }),
			@ApiResponse(responseCode = "400", description = "BAD_REQUEST, algún campo no es correcto", content = @Content),
			@ApiResponse(responseCode = "404", description = "Pago no encontrado", content = @Content) })
	@GetMapping(value = "/aforoTotal/{aforoTotal}/entradasVendidas/{entradasVendidas}/precio/{precio}")
	public MensajePago verificaPago(@PathVariable int aforoTotal, @PathVariable int entradasVendidas, @PathVariable float precio) {
		return pagoService.generaMensajeDePago(aforoTotal, entradasVendidas, precio, RandomTarjeta.creaTarjeta());
	}
}
