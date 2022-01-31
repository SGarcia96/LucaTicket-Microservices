package com.example.pago.service;

import com.example.pago.model.MensajePago;

public interface PagoService {
	public MensajePago generaMensajeDePago(int aforoTotal, int entradasVendidas, float precio);
}
