package com.example.usuario.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.usuario.model.EventoDTO;

//Se añade este nombre : spring.application.name=evento
//Indico que voy a contactar con esa ruta
@FeignClient(name = "evento", url= "http://localhost:7777")
public interface EventoFeignClient {
	
	@GetMapping("/eventos/{id}")
    EventoDTO getEvento (@PathVariable String id);
}
