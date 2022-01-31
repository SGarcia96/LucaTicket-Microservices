package com.example.usuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.example.usuario.feignclients.EventoFeignClient;
import com.example.usuario.feignclients.PagoFeignClient;
import com.example.usuario.model.Entrada;
import com.example.usuario.model.EventoDTO;
import com.example.usuario.model.Usuario;
import com.example.usuario.repository.EntradaRepository;
import com.example.usuario.repository.UsuarioRepository;

@Repository
public class EntradaServiceImpl implements EntradaService {

	@Autowired
	private EntradaRepository entradaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EventoFeignClient eventoFeign;
	

	@Autowired
	private PagoFeignClient pagoFeignClient;

	@Override
<<<<<<< HEAD
	public ResponseEntity<?> addEntrada(Long idUsuario, String idEvento) {
		final EventoDTO evento = EventoDTO.of(eventoFeign.getEvento(idEvento));
		ResponseEntity<?> response = pagoFeignClient.verificaPago(evento.getAforo(), 100, evento.getPrecio());
		if(response.getStatusCode().value() == 200) {
			this.saveEntrada(idUsuario, evento);	
		}
		return response;
=======
	public void addEntrada(Long idUsuario, String idEvento) {
		final EventoDTO evento = eventoFeign.getEvento(idEvento);

		this.saveEntrada(idUsuario, evento);
>>>>>>> 0cb469532992427dc561ff7f608c7ea30cab9790
	}

	@Override
	public Entrada saveEntrada(Long idUsuario, EventoDTO evento) {
		Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow();
		Entrada entrada = new Entrada();
		entrada.setUsuario(usuario.getId());
		entrada.setEvento(evento);

		return entradaRepository.save(entrada);
	}

	@Override
	public List<Entrada> findAll() {
		return entradaRepository.findAll();
	}

}
