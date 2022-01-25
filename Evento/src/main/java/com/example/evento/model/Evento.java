package com.example.evento.model;

import java.util.Date;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
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
	private String id;
	
	@NotEmpty
	@Size(min=3, max=30)
	private String nombre;
<<<<<<< HEAD
	
	@NotEmpty
	private String descripcionCorta;
	
	@NotEmpty
	private String descripcionLarga;
	
	@NotEmpty 
	private String fotoUrl;
	
	@NotEmpty
	@FutureOrPresent
	@DateTimeFormat(pattern="dd/MM/yyyy") 
	private Date fechaEvento;
	
=======

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

>>>>>>> d83016854ff64a76eed01bf257ea23332c84ed1d
	@NotEmpty
	@DateTimeFormat(pattern="hh:mm") 
	private Date horaEvento;
	
	@NotEmpty
	private float[] rangoPrecios;
<<<<<<< HEAD
	
	@NotEmpty
=======

	@NotEmpty(message = "La politicaAcceso no debe ser vacío")
>>>>>>> d83016854ff64a76eed01bf257ea23332c84ed1d
	private String politicaAcceso;
	
	@NotEmpty
	private Recinto recinto;
	
}
