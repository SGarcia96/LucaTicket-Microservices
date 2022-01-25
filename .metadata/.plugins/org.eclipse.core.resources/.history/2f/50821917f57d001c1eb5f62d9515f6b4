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
	private String nombre;
	private String descripcionCorta;
	private String descripcionLarga;
	private String fotoUrl;
	private Date fechaEvento;
	private String horaEvento;
	private float[] rangoPrecios;
	private String politicaAcceso;
	private Recinto recinto;

}
