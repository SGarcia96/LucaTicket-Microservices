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

@Document("Evento")
@Data
@NoArgsConstructor
public class EventoDTO {
	@Id
	private String id;
	
	@NotEmpty
	@Size(min=3, max=30)
	private String nombre;
	
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
	
	@NotEmpty
	@DateTimeFormat(pattern="hh:mm") 
	private Date horaEvento;
	
	@NotEmpty
	private float[] rangoPrecios;
	
	@NotEmpty
	private String politicaAcceso;
	 	
	// private Recinto recinto;
	
}
