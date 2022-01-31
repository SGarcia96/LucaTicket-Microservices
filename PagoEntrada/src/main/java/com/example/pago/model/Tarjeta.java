package com.example.pago.model;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.example.pago.model.Tarjeta;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tarjeta {
	
	@NotEmpty
	@Size(min=16, max=16)
	private long numero;
	
	@NotEmpty
	@Size(min=3, max=3)
	private int cvv; 
	
	@NotEmpty
	@Future
	LocalDate fecha;
	
	@NotEmpty
	private float saldo;
	
}
