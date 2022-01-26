package com.example.usuario.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "usuarios", 
		uniqueConstraints={
                   @UniqueConstraint(columnNames = "id"),
                   @UniqueConstraint(columnNames = "mail")
                  }
)
//@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Usuario", description = "Clase Usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(name = "id", description = "Identificador único para el usuario", example = "1", required = true)
	private long id;

	@NotEmpty(message = "El nombre no debde ser vacío")
	@Size(min = 3, max = 30)
	private String nombre;

	@NotEmpty(message = "Necesitamos que indigues un apellido")
	@Size(min = 3, max = 30)
	private String apellido;

	@NotEmpty(message = "El mail no debe ser vacio")
	@Email
	@Column(unique = true)
	private String mail;

	@NotEmpty(message = "Indica un password")
	@Size(min = 5)
	private String password;

	@Column(name = "fecha_alta")
	private LocalDate fechaAlta;
}
