package com.example.usuario;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;

import com.example.usuario.model.Entrada;
import com.example.usuario.model.EventoDTO;
import com.example.usuario.model.Usuario;

@ActiveProfiles("dev")
public class EntradaControllerTests {
	private static final Logger logger = LoggerFactory.getLogger(EntradaControllerTests.class);
	
	@BeforeAll
	public static void setup() {
		baseURI = "http://localhost:8888";
		basePath = "/entrada";
	}
	
	@Test
	public void shouldGetAllEntradasWithStatus200() {
		when()
			.get()
		.then()
			.statusCode(200);
	}
	
	@Test
	public void shouldAddEntradaWithStatus201() {
		logger.info("----TEST ENTRADA CREADA-----");
		Entrada entrada = new Entrada();
		entrada.setEvento(new EventoDTO());
		entrada.setUsuario(16);
		logger.info(entrada.toString());
		
		given()
			.contentType("application/json")
			.body(entrada)
		.when()
			.post()
		.then()
			.statusCode(201)
			.body("evento", equalTo("eventillo"));
	}

	@Test
	public void shouldReturnAnErrorMessageAndStatus400() {
		logger.info("----TEST CAMPO VACIO-----");
		Entrada entrada = new Entrada();
		entrada.setUsuario(1111111111);
		//No se inserta evento
		logger.info(entrada.toString());
		
		given()
			.contentType("application/json")
			.body(entrada)
		.when()
			.post()
		.then()
			.statusCode(400)
			.body("error", equalTo("BAD_REQUEST"))
			.body("message[0]",equalTo("evento: Necesitamos que se indique el evento"));
	}
}

