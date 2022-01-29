package com.example.usuario.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Evento {

	private String idEvento;
	private String nombre;
	private Recinto recinto;
	private float[] rangoPrecios;

}
