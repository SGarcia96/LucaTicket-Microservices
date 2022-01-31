package com.example.usuario.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.usuario.model.Evento;

@FeignClient(name = "evento", url = "http://localhost:7777")
public interface EventoFeignClient {

	@GetMapping("/eventos/{id}")
	Evento getEvento(@PathVariable String id);
}
