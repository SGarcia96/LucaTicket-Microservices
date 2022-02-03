package com.example.usuario.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.usuario.controller.error.EntradaNotFoundException;
import com.example.usuario.feignclients.EventoFeignClient;
import com.example.usuario.feignclients.PagoFeignClient;
import com.example.usuario.model.Entrada;
import com.example.usuario.model.EventoDTO;
import com.example.usuario.model.MensajePago;
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
	public Map<String, Object> addEntrada(Long idUsuario, String idEvento) {
		Map<String, Object> body = new LinkedHashMap<>();
		final EventoDTO evento = eventoFeign.getEvento(idEvento);
		MensajePago mensajePago = pagoFeignClient.verificaPago(evento.getAforo(), entradaRepository.findAllByEventoNombre(evento.getNombre()).size(), evento.getPrecio());
		body.put("timestamp", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
		body.put("message", mensajePago.getMensaje());
		body.put("status", mensajePago.getCodigo());
		if(mensajePago.getCodigo().value() == 200) {
			saveEntrada(idUsuario, evento);
			body.put("evento", evento);
		}
		return body;
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
		final List<Entrada> lista = entradaRepository.findAll();
		if (lista.isEmpty()) {
			throw new EntradaNotFoundException("No se ha vendido ninguna entrada aún");
		}
		return lista;
	}

}
