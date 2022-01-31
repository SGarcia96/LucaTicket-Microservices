package com.example.usuario.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.usuario.model.EventoDTO;

@FeignClient(name = "evento", url = "http://localhost:7777/eventos/")
public interface EventoFeignClient {

	@GetMapping("{id}")
	EventoDTO getEvento(@PathVariable String id);
}
