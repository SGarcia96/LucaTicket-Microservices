package com.example.usuario;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.usuario.model.Usuario;


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
			//.assertThat()
			//.body("size()", greaterThan(0));
			
	}
	
	@Test
	public void shouldAddUsuarioWithStatus201() {
		logger.info("eeeeeeeeeeeeeeeeeeeeeeeeee");
		Usuario usuario = new Usuario();
		
		usuario.setNombre("Dimitri");
		usuario.setApellido("Kruchenko");
		usuario.setMail("d.kruchenko@gmail.com");
		usuario.setPassword("Ak47top");
		usuario.setFechaAlta(LocalDate.now(ZoneId.of("Europe/Madrid")));
		logger.info(usuario.toString());
		
		given()
			.contentType("application/json")
			.body(usuario)
		.when()
			.post()
		.then()
			.statusCode(201)
			.body("apellido", equalTo("Kruchenko"));
}
	@Test
	public void shouldReturnAnErrorMessageAndStatus400() {
		logger.info("----TEST CAMPO VACIO-----");
		Usuario usuario = new Usuario();
		
		usuario.setNombre("Vladimir");
		usuario.setMail("yesiamputin@gmail.com");
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
			.body("message[0]",equalTo("apellido: Necesitamos que indigues un apellido"));
			
			
		
		}
	}

