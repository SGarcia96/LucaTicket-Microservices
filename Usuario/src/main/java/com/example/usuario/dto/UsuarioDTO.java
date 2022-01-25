package com.example.usuario.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(name="UsuarioDTO", description = "Clase UsuarioDTO")
public class UsuarioDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String nombre;
	private String apellido;
	private String mail;
	private String password;
	private Date fechaAlta;

}
