package com.example.pago.service;

import com.example.pago.model.MensajePago;
import com.example.pago.model.Tarjeta;

public interface PagoService {

	public MensajePago generaMensajeDePago(int aforoTotal, int entradasVendidas, float precio, Tarjeta tarjeta);

}
