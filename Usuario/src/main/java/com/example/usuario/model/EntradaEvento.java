package com.example.usuario.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class EntradaEvento {
	
	private String id;
	private String nombre;
	@OneToOne
	@JoinColumn
	private Entrada entrada;
	private int aforo;
	private float[] rangoPrecios;

}

/********** Lo dejamos por si acaso ************/