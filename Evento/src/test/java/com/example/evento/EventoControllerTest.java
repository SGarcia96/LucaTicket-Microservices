package com.example.evento;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.evento.model.Evento;
import com.example.evento.model.Recinto;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

import java.util.Date;

public class EventoControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(EventoControllerTest.class);
	
	@BeforeAll
	public static void setup() {
		baseURI = "http://localhost:7777";
		basePath = "/eventos";
		port = 8080;
	}
	
	// GET /eventos
	@Test
	public void shouldGetAllEventosWithStatus200() {
		when()
			.get()
		.then()
			.statusCode(200)
			.assertThat()
			.body("size()", greaterThan(0));
	}
	
	// POST /eventos
	@SuppressWarnings("deprecation")
	@Test
	@Disabled
	public void shouldAddEventoWithStatus201() {
		logger.info("eeeeeeeeeeeeeeeeeeeeeeeeee");
		Evento evento = new Evento();
		
		evento.setNombre("ntest");
		evento.setDescripcionCorta("dcorta");
		evento.setDescripcionLarga("dlarga");
		evento.setFotoUrl("m.jpg");
		evento.setFechaEvento(new Date(2000,04,12));
		evento.setHoraEvento("20:00");
		evento.setPoliticaAcceso("pacc");
		evento.setRangoPrecios(new float[] {(float) 1.1, (float) 2.2});
		evento.setRecinto(new Recinto("a", "b", "c", 10));
		logger.info(evento.toString());
			
		given()
			.contentType("application/json")
			.body(evento)
		.when()
			.post()
		.then()
			.statusCode(201)
			.body("nombre", equalTo("ntest"));
				
	}
	
	// POST /eventos
	@Test
	public void shouldReturnAnErrorMessageAndStatus400() {
		Evento evento = new Evento();
		evento.setNombre("");
		evento.setDescripcionCorta("dcorta");
		evento.setDescripcionLarga("dlarga");
		evento.setFotoUrl("m.jpg");
		evento.setFechaEvento(new Date(2000,04,12));
		evento.setHoraEvento("20:00");
		evento.setPoliticaAcceso("pacc");
		evento.setRangoPrecios(new float[] {(float) 1.1, (float) 2.2});
		evento.setRecinto(new Recinto("a", "b", "c", 10));
		
		given()
			.contentType("application/json")
			.body(evento)
		.when()
			.post()
		.then()
			.statusCode(400)
			.body("error", equalTo("BAD_REQUEST"))
//			.body("message[0]", equalTo("nombre: no debe estar vacío"))
			.body("message[1]", equalTo("nombre: el tamaño debe estar entre 3 y 30"));
	}
	
	// GET /eventos/{id}
	@Test
	public void shouldGetEventoByIdWithStatus200() {
		when()
			.get("/61f012bd80dacc7180e36747")
		.then()
			.statusCode(200)
			.assertThat()
			.body("size()", greaterThan(0))
			.body("nombre", equalTo("eventito 2.0"));
	}
	
	@Test void shouldReturnAnErrorMessageAndStatus404() {
		when()
			.get("/1")
		.then()
			.statusCode(404)
			.body("error", equalTo("Not Found"))
			.body("message", equalTo("Epic Fail: No existe el evento"));
	}
}
