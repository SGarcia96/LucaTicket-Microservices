package com.example.pago.utils;

import java.time.LocalDate;

import com.example.pago.model.Tarjeta;

public class RandomTarjeta {

	public static Tarjeta creaTarjeta() {
		int randomNumber = RandomNumber.creaNumeroRandom();
		
		if(randomNumber < 2) {
			return creaTarjetaSinSaldo();
		} else if(randomNumber == 2) {
			return creaTarjetaCaducada();
		} else if(randomNumber == 3) {
			return creaTarjetaErronea();
		} else {
			return creaTarjetaCorrecta();
		}
	}
	
	public static Tarjeta creaTarjetaCorrecta() {
		int randomNumber = RandomNumber.creaNumeroRandom();
		return new Tarjeta(randomNumber * 1000_000_000_000_000L, randomNumber * 100, LocalDate.of(2030, 1, 1), 100000.00F);
	}
	public static Tarjeta creaTarjetaCaducada() {
		int randomNumber = RandomNumber.creaNumeroRandom();
		return new Tarjeta(randomNumber * 1000_000_000_000_000L, randomNumber * 100, LocalDate.of(2000, 1, 1), 100000.00F);
	}
	public static Tarjeta creaTarjetaErronea() {
		int randomNumber = RandomNumber.creaNumeroRandom();
		return new Tarjeta(randomNumber * 100_000_000_000_000L, randomNumber * 1000, LocalDate.of(2030, 1, 1), 100000.00F);
	}
	public static Tarjeta creaTarjetaSinSaldo() {
		int randomNumber = RandomNumber.creaNumeroRandom();
		return new Tarjeta(randomNumber * 1000_000_000_000_000L, randomNumber * 100, LocalDate.of(2030, 1, 1), 0.01F);
	}
	
}
