package com.example.usuario.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class EventoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;
	private int aforo;
	private float precio;

}
