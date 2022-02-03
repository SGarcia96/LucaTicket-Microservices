package com.example.usuario.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.usuario.model.MensajePago;

@FeignClient(name = "pago", url = "http://localhost:9000")
public interface PagoFeignClient {

	@GetMapping(value = "/pago/aforoTotal/{aforoTotal}/entradasVendidas/{entradasVendidas}/precio/{precio}")
	MensajePago verificaPago(@PathVariable int aforoTotal, @PathVariable int entradasVendidas, @PathVariable float precio);

}
