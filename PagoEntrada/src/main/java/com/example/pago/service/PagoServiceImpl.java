package com.example.pago.service;

import org.springframework.stereotype.Service;

import com.example.pago.model.MensajePago;
import com.example.pago.utils.RandomNumber;

@Service
public class PagoServiceImpl implements PagoService {
	
	// crear metodo para generar un codigo aleatorio [codigo, mensaje]
	public MensajePago generaMensajeDePago() {
		int randomNumber = RandomNumber.creaNumeroRandom();
		
		if(randomNumber < 5) {
			return new MensajePago(200, "correcto");
		} else if(randomNumber == 6) {
			return new MensajePago(202, "menos correcto");
		} else if(randomNumber == 7) {
			return new MensajePago(203, "peor correcto");
		} else {
			return new MensajePago(400, "caca");
		}
	}
}
