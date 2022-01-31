package com.example.pago.service;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.pago.model.MensajePago;
import com.example.pago.model.Tarjeta;
import com.example.pago.utils.RandomNumber;
import com.example.pago.utils.RandomTarjeta;

@Service
public class PagoServiceImpl implements PagoService {
	
	@Override
	public MensajePago generaMensajeDePago(int aforoTotal, int entradasVendidas, float precio) {
		Tarjeta tarjeta = RandomTarjeta.creaTarjeta();
		
		if(aforoTotal <= entradasVendidas) {
			return new MensajePago("aforo completo", HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
		} else if(tarjeta.getFecha().isBefore(LocalDate.now())) {
			return new MensajePago("tarjeta caducada", HttpStatus.NOT_ACCEPTABLE);
		} else if(tarjeta.getNumero() < 1000_000_000_000_000L || tarjeta.getNumero() >= 10_000_000_000_000_000L ||  
					tarjeta.getCvv() < 100 || tarjeta.getCvv() >= 1000) {
			return new MensajePago("los datos de la tarjeta son erroneos", HttpStatus.NOT_ACCEPTABLE);
		} else if(tarjeta.getSaldo() < precio) {
			return new MensajePago("dispone de un saldo insuficiente", HttpStatus.EXPECTATION_FAILED);
		} else {
			return new MensajePago("Pago efectuado con éxito", HttpStatus.OK);
		}
	}
}
