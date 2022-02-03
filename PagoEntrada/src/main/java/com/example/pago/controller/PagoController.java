package com.example.pago.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@Operation(summary = "Verificar pago", description = "Dada una entrada comprada, devuelve información del pago", tags = {
			"pago" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pago correcto", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = MensajePago.class)) }),
			@ApiResponse(responseCode = "406", description = "Tarjeta caducada", content = @Content),
			@ApiResponse(responseCode = "406", description = "Datos incorrectos", content = @Content), 
			@ApiResponse(responseCode = "417", description = "Saldo insuficiente", content = @Content),
			@ApiResponse(responseCode = "451", description = "Aforo completo", content = @Content)}) 
	@GetMapping(value = "/aforoTotal/{aforoTotal}/entradasVendidas/{entradasVendidas}/precio/{precio}")
	public MensajePago verificaPago(@PathVariable int aforoTotal, @PathVariable int entradasVendidas, @PathVariable float precio) {
		log.info("------- en verificaPago del controller");
		return pagoService.generaMensajeDePago(aforoTotal, entradasVendidas, precio, RandomTarjeta.creaTarjeta());
	}
	
}
