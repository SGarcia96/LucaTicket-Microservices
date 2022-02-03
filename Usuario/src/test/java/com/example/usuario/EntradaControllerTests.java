package com.example.usuario;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;

import com.example.usuario.model.Entrada;
import com.example.usuario.model.EventoDTO;

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
}

