package com.example.evento;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;

import com.example.evento.model.Evento;
import com.example.evento.model.Recinto;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.mockito.ArgumentMatchers.contains;

import java.time.LocalDate;

/* Estos test solo funcionan si está levantado el server de Eureka */
@ActiveProfiles("dev")
public class EventoControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(EventoControllerTest.class);
	
	@BeforeAll
	public static void setup() {
		logger.info("------> INICIANDO TEST");
		baseURI = "http://localhost:7777";
		basePath = "/eventos";
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
	@Test
	@Disabled
	public void shouldAddEventoWithStatus201() {
		Evento evento = new Evento();
		
		evento.setNombre("ntest");
		evento.setDescripcionCorta("dcorta");
		evento.setDescripcionLarga("dlarga");
		evento.setFotoUrl("m.jpg");
		evento.setGenero("hombre");
		evento.setFechaEvento(LocalDate.now());
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
			.statusCode(201)
			.body("nombre", equalTo("ntest"));
	}
	
	// POST /eventos
	@Test
	public void shouldReturnAnErrorMessageAndStatus400WhenAnAttributteIsEmpty() {
		Evento evento = new Evento();
		evento.setNombre("");
		evento.setDescripcionCorta("dcorta");
		evento.setDescripcionLarga("dlarga");
		evento.setFotoUrl("m.jpg");
		evento.setGenero("rock");
		evento.setFechaEvento(LocalDate.now());
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
			.body("message[0]", equalTo("nombre: no debe estar vacío"));
		//	.body("message[1]", equalTo("nombre: el tamaño debe estar entre 3 y 30"));
	}
	
	// GET /eventos/{id}
	@Test
	public void shouldGetEventoByIdWithStatus200() {
		when()
			.get("/61f112f0670e7222a44331bd")
		.then()
			.statusCode(200)
			.assertThat()
			.body("size()", greaterThan(0))
			.body("nombre", equalTo("eventitoUpdate2"));
	}
	
	// GET /eventos/{id}
	@Test 
	void shouldReturnAnErrorMessageAndStatus404WhenIdNotExist() {
		when()
			.get("/1")
		.then()
			.statusCode(404)
			.body("error", equalTo("Not Found"))
			.body("message", equalTo("Epic Fail: No existe el evento"));
	}
	
	// DELETE /eventos/{id}
	@Test
	@Disabled
	public void shouldReturnStatus200WhenDeleteSuccessful() {
		when()
			.delete("/61f012bd80dacc7180e36747")
		.then()
			.statusCode(200);
	}
	
	// DELETE /eventos/{id}
	@Test
	public void shouldReturnStatus404NotFound() {
		when()
			.delete("/abcdfghi111111111")
		.then()
			.statusCode(404);
	}
	
	// GET /eventos/findAllByGenero/{genero}
	@Test
	public void shouldGetEventoByGeneroWithStatus200() {
		when()
			.get("findAllByGenero/rock")
			.then()
			.statusCode(200)
			.assertThat()
			.body("size()", greaterThan(0))
			.body("genero[0]", equalTo("rock"));
	}
	
	// GET /eventos/findAllByGenero/{genero}
	@Test
	public void shouldGetEventoByGeneroWithStatus404() {
		when()
			.get("findAllByGenero/rack")
			.then()
			.statusCode(404)
			.assertThat()
			.body("error", equalTo("Not Found"));
	}

	// GET /eventos/findAllByNombre{nombre}
	@Test
	public void shouldGetEventoByNombreWithStatus200() {
		when()
			.get("/findAllByNombre/eventitoFecha2")
		.then()
			.statusCode(200)
			.assertThat()
			.body("size()", greaterThan(0))
			.body("id[0]", equalTo("61f142e9be1b54605e3893d0"));
	}
	
	// GET /eventos/findAllByLugar{lugar}
	@Test
	public void shouldGetEventoByLugarWithStatus200() {
		when()
			.get("/findAllByCiudad/cadi")
		.then()
			.statusCode(200)
			.assertThat()
			.body("size()", greaterThan(0))
			.body("recinto[0].lugar", equalTo("cadi"));
	}
	
	// GET /eventos/findAllByLugar{lugar}
	@Test
	public void shouldGetEventoByLugarWithStatus404() {
		when()
			.get("/findAllByCiudad/abcdef")
		.then()
			.statusCode(404)
			.assertThat()
			.body("size()", greaterThan(0));
	}
	
	
	// PUT /eventos/{id}
	@Test
	public void shouldUpdateEventoWithStatus200() {
		Evento evento = new Evento();
		evento.setNombre("eventitoUpdate2");
		evento.setDescripcionCorta("dcorta");
		evento.setDescripcionLarga("dlarga");
		evento.setFotoUrl("m.jpg");
		evento.setGenero("pop");
		evento.setFechaEvento(LocalDate.now());
		evento.setHoraEvento("20:00");
		evento.setPoliticaAcceso("pacc");
		evento.setRangoPrecios(new float[] {(float) 1.1, (float) 2.2});
		evento.setRecinto(new Recinto("a", "b", "c", 10));
		
		given()
			.contentType("application/json")
			.body(evento)	
		.when()
			.put("/61f112f0670e7222a44331bd")
		.then()
			.statusCode(200)
			.body("genero", equalTo("pop"));
			
	}
	
	// PUT /eventos/{id}
		@Test
		public void shouldUpdateEventoWithStatus400() {
			Evento evento = new Evento();
			evento.setNombre("");
			evento.setDescripcionCorta("dcorta");
			evento.setDescripcionLarga("dlarga");
			evento.setFotoUrl("m.jpg");
			evento.setGenero("rock");
			evento.setFechaEvento(LocalDate.now());
			evento.setHoraEvento("20:00");
			evento.setPoliticaAcceso("pacc");
			evento.setRangoPrecios(new float[] {(float) 1.1, (float) 2.2});
			evento.setRecinto(new Recinto("a", "b", "c", 10));
			
			given()
				.contentType("application/json")
				.body(evento)	
			.when()
				.put("/abcdf1234")
			.then()
				.statusCode(400)
				.body("nombre", equalTo(null));		
		}
	
}
