package com.example.usuario.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pago", url = "http://localhost:9000")
public interface PagoFeignClient {

	@PostMapping(value = "/pago/aforoTotal/{aforoTotal}/entradasVendidas/{entradasVendidas}/precio/{precio}")
	ResponseEntity<?> verificaPago(@PathVariable int aforoTotal, @PathVariable int entradasVendidas, @PathVariable float precio);
}
