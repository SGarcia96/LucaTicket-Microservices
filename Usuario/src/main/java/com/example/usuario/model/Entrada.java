package com.example.usuario.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "entrada", uniqueConstraints = { @UniqueConstraint(columnNames = "id"), })
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Entrada", description = "Clase Entrada")
public class Entrada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(name = "id", description = "Identificador único para la entrada", example = "1", required = true)
	private long id;

	private boolean vip;

	private long idUsuario;

	@NotEmpty(message = "Necesitamos que se indique el evento")
	private String evento;
}
