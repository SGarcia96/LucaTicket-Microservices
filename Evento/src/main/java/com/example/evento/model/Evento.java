package com.example.evento.model;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document("eventos")
@Data
@NoArgsConstructor
public class Evento {
	@Id
<<<<<<< HEAD
	private String id;
=======
	private String id ;
>>>>>>> 3c01281e70679effe1851423d1c045d69a817d79
	
	@NotEmpty
	@Size(min=3, max=30)
	private String nombre;

	@NotEmpty(message = "La descripcionCorta no debe ser vacío")
	private String descripcionCorta;

	@NotEmpty(message = "La descripcionLarga no debe ser vacío")
	private String descripcionLarga;

	@NotEmpty(message = "La fotoUrl no debe ser vacío")
	private String fotoUrl;

	@NotNull
	@FutureOrPresent
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	private LocalDate fechaEvento;

	@NotEmpty
<<<<<<< HEAD
	@DateTimeFormat(pattern="hh:mm") 
=======
	@JsonFormat(pattern = "hh:mm")
>>>>>>> 3c01281e70679effe1851423d1c045d69a817d79
	private String horaEvento;
	
	@NotEmpty
	private float[] rangoPrecios;

	@NotEmpty(message = "La politicaAcceso no debe ser vacío")
	private String politicaAcceso;
	
<<<<<<< HEAD
	@NotEmpty
=======
	@NotEmpty(message = "El género no debe ser vacío")
	private String genero;

	@NotNull
>>>>>>> 3c01281e70679effe1851423d1c045d69a817d79
	private Recinto recinto;
	
}
