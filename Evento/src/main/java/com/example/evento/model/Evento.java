package com.example.evento.model;

import java.util.Date;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document("eventos")
@Data
@NoArgsConstructor
public class Evento {
	@Id
	private String id ;
	
	@NotEmpty
	@Size(min = 3, max = 30)
	private String nombre;

	@NotEmpty(message = "La descripcionCorta no debe ser vacío")
	private String descripcionCorta;

	@NotEmpty(message = "La descripcionLarga no debe ser vacío")
	private String descripcionLarga;

	@NotEmpty(message = "La fotoUrl no debe ser vacío")
	private String fotoUrl;

	@NotNull
	@FutureOrPresent
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date fechaEvento;

	@NotEmpty
	//@DateTimeFormat(pattern = "hh:mm")
	private String horaEvento;

	@NotEmpty
	private float[] rangoPrecios;

	@NotEmpty(message = "La politicaAcceso no debe ser vacío")
	private String politicaAcceso;
	
	@NotEmpty(message = "El género no debe ser vacío")
	private String genero;

	@NotNull
	private Recinto recinto;

}
