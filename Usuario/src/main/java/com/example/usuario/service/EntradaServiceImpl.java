package com.example.usuario.service;

import java.util.List;

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
	public MensajePago addEntrada(Long idUsuario, String idEvento) {
		final EventoDTO evento = eventoFeign.getEvento(idEvento);
		MensajePago response = pagoFeignClient.verificaPago(evento.getAforo(), entradaRepository.findAllByEvento(evento).size(), evento.getPrecio());
		if(response.getCodigo().value() == 200) {
			saveEntrada(idUsuario, evento);	
		}
		return response;
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
