package com.example.usuario.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Schema(name="Usuario", description = "Clase Usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name= "id", 
			description = "Identificador único para el usuario", 
			example = "1", 
			required = true)
	private long id;
	
	@NotNull
	@Size(min = 3, max = 30)
	private String nombre;
	
	@NotNull
	@Size(min = 3, max = 30)
	private String apellido;
	
	@NotNull
	@Email
	private String mail;
	
	@NotNull
	@Size(min = 5)
	private String password;
	
	@Positive
	@Column(name = "fecha_alta")
	private Date fechaAlta;
}
