package com.example.evento.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Evento")
@Data
@NoArgsConstructor
public class EventoDTO {
	
	@Id
	private String id;
	private String nombre;
	private float precio;
	private int aforo;

}
