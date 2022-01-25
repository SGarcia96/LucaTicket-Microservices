package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/evento")
public class EventoController {

	private static final Logger log = LoggerFactory.getLogger(EventoController.class);
	
	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private EventoAdapter eventoAdapter;
	
	@GetMapping("/{id}")
	public EventoResponse getEvento(@PathVariable Long id) {
		log.info("--- evento por id " + id);
		final Evento evento = eventoRepository.findId(id).orElseThrow();
		return eventoAdapter.of(evento);
	}
}
