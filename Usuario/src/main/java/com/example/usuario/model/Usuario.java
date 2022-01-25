package com.example.usuario.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
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
	
	@NotEmpty(message = "El nombre no debde ser vacío")
	private String firstName;
	
	@NotEmpty(message = "Necesitamos que indigues un apellido")
	private String lastName;
	
	@NotEmpty
	@Size(min = 3, max = 30)
	private String nombre;
	
	@NotEmpty
	@Size(min = 3, max = 30)
	private String apellido;
	
	@NotEmpty
	@Email
	private String mail;
	
	@NotEmpty
	@Size(min = 5)
	private String password;
	
	@NotEmpty
	@Positive
	@Column(name = "fecha_alta")
	private Date fechaAlta;
}
