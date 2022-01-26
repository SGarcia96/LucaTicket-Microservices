package com.example.evento.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Evento")
@Data
@NoArgsConstructor
public class EventoDTO {
	@Id
	private String id ;
	private String nombre;
	private String descripcionCorta;
	private String descripcionLarga;
	private String fotoUrl;
	private LocalDate fechaEvento;
	private String horaEvento;
	private float[] rangoPrecios;
	private String politicaAcceso;
	private String genero;
	private Recinto recinto;

}
