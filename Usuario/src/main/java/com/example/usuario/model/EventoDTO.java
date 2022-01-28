package com.example.usuario.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import com.example.usuario.utils.RandomNumber;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class EventoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String evento;
	private int aforo;
	private float precio;

	public static EventoDTO of(Evento evento) {
		EventoDTO eventoDTO = new EventoDTO();
		eventoDTO.setEvento(evento.getNombre());
		eventoDTO.setAforo(evento.getAforo());
		float[] rangoPrecios = evento.getRangoPrecios();
		eventoDTO.setPrecio(RandomNumber.creaFloatRandom(rangoPrecios[1], rangoPrecios[0]));

		return eventoDTO;
	}

}
