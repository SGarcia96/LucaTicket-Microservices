package com.example.evento.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Recinto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recinto {

	@NotEmpty(message = "El nombre del recinto no debe ser vacío")
	private String nombre;

	@NotEmpty(message = "El nombre del recinto no debe ser vacío")
	private String lugar;

	@NotEmpty(message = "La direccion del recinto no debe ser vacío")
	private String direccion;

	@NotEmpty
	private int aforo;

}
