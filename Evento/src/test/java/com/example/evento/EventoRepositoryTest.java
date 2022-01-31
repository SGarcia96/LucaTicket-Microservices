package com.example.evento;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.TestPropertySource;

import com.example.evento.model.Evento;
import com.example.evento.model.Recinto;
import com.example.evento.repository.EventoRepository;

@DataMongoTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
public class EventoRepositoryTest {

	@Autowired
	EventoRepository eventoRepository;

	@Test
	void shouldReturnTheListWhenFindAll() {
		List<Evento> eventos = eventoRepository.findAll();
		assertThat(eventos).hasSize(eventos.size());
	}

	@Test
	void shouldSaveTheEventSavedCorrectly() {
		Evento evento = new Evento();
		evento.setNombre("ntest");
		evento.setDescripcionCorta("dcorta");
		evento.setDescripcionLarga("dlarga");
		evento.setFotoUrl("m.jpg");
		evento.setGenero("rocktest");
		evento.setFechaEvento(LocalDate.now());
		evento.setHoraEvento("20:00");
		evento.setPoliticaAcceso("pacc");
		evento.setRangoPrecios(new float[] {(float) 1.1, (float) 2.2});
		evento.setRecinto(new Recinto("a", "b", "c", 10));
		
		eventoRepository.save(evento);
		assertThat(eventoRepository.findAll())
			.extracting(Evento::getNombre)
			.contains("ntest");
	}
	
	@Test
	void shouldReturnOnlyTheEventsWithTheGeneroToFindByGenero() {
		Evento evento = new Evento();
		evento.setNombre("ntest");
		evento.setDescripcionCorta("dcorta");
		evento.setDescripcionLarga("dlarga");
		evento.setFotoUrl("m.jpg");
		evento.setGenero("rocktest2");
		evento.setFechaEvento(LocalDate.now());
		evento.setHoraEvento("20:00");
		evento.setPoliticaAcceso("pacc");
		evento.setRangoPrecios(new float[] {(float) 1.1, (float) 2.2});
		evento.setRecinto(new Recinto("a", "b", "c", 10));
		
		eventoRepository.save(evento);
		assertThat(eventoRepository.findAllByGenero("rocktest2"))
			.hasSize(1)
			.extracting(Evento::getGenero).contains("rocktest2");
	}
	
	@Test
	void shouldReturnOnlyTheEventsWithTheNombreToFindByNombre() {
		Evento evento = new Evento();
		evento.setNombre("nombretest");
		evento.setDescripcionCorta("dcorta");
		evento.setDescripcionLarga("dlarga");
		evento.setFotoUrl("m.jpg");
		evento.setGenero("rocktest");
		evento.setFechaEvento(LocalDate.now());
		evento.setHoraEvento("20:00");
		evento.setPoliticaAcceso("pacc");
		evento.setRangoPrecios(new float[] {(float) 1.1, (float) 2.2});
		evento.setRecinto(new Recinto("a", "b", "c", 10));
		
		eventoRepository.save(evento);
		assertThat(eventoRepository.findByNombre("nombretest"))
			.hasSize(1)
			.extracting(Evento::getNombre).contains("nombretest");
	}
	
	@Test
	void shouldReturnEmptyListIfFindAnEventAfterDelete() {
		Evento evento = new Evento();
		evento.setId("todelete");
		evento.setNombre("eventdel");
		evento.setDescripcionCorta("dcorta");
		evento.setDescripcionLarga("dlarga");
		evento.setFotoUrl("m.jpg");
		evento.setGenero("rocktest");
		evento.setFechaEvento(LocalDate.now());
		evento.setHoraEvento("20:00");
		evento.setPoliticaAcceso("pacc");
		evento.setRangoPrecios(new float[] {(float) 1.1, (float) 2.2});
		evento.setRecinto(new Recinto("a", "b", "c", 10));
		
		eventoRepository.save(evento);
		eventoRepository.deleteById("todelete");
		assertThat(eventoRepository.findByNombre("eventdel")).isEmpty();
	}

}
