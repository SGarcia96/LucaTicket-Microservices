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

import com.example.usuario.model.Usuario;

@ActiveProfiles("dev")
public class UsuarioControllerTests {
	private static final Logger logger = LoggerFactory.getLogger(UsuarioControllerTests.class);
	
	@BeforeAll
	public static void setup() {
		baseURI = "http://localhost:8888";
		basePath = "/usuarios";
	}
	
	@Test
	public void shouldGetAllUsuariosWithStatus200() {
		when()
			.get()
		.then()
			.statusCode(200);
	}
	
	@Test
	public void shouldAddUsuarioWithStatus201() {
		logger.info("----TEST USUARIO CREADO-----");
		Usuario usuario = new Usuario();
		
		usuario.setNombre("Juan");
		usuario.setApellido("Martinez");
		usuario.setMail("j.martino@gmail.com");
		usuario.setPassword("12345");
		usuario.setFechaAlta(LocalDate.now(ZoneId.of("Europe/Madrid")));
		logger.info(usuario.toString());
		
		given()
			.contentType("application/json")
			.body(usuario)
		.when()
			.post()
		.then()
			.statusCode(201)
			.body("apellido", equalTo("Martinez"));
	}

	@Test
	public void shouldReturnAnErrorMessageAndStatus400() {
		logger.info("----TEST CAMPO VACIO-----");
		Usuario usuario = new Usuario();
		usuario.setNombre("Juan");
		//No se inserta apellido
		usuario.setMail("j.martino@gmail.com");
		usuario.setPassword("12345");
		usuario.setFechaAlta(LocalDate.now(ZoneId.of("Europe/Madrid")));
		logger.info(usuario.toString());
		
		given()
			.contentType("application/json")
			.body(usuario)
		.when()
			.post()
		.then()
			.statusCode(400)
			.body("error", equalTo("BAD_REQUEST"))
			.body("message[0]",equalTo("apellido: Necesitamos que indiques un apellido"));
	}
	
	@Test
	public void shouldLogUsuarioWithStatus200() {
		logger.info("----TEST LOGIN------");
		given()
			.contentType("application/json")
		.when()
			.post("/login?usuario=andrea@gmail.com&password=andrea123")
		.then()
			.statusCode(200);
	}
}

