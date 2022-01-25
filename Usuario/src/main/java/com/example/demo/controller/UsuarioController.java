package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@PostMapping("/usuarios")
	public String add(Usuario usuario, Model model) {
		model.addAttribute("usuario", usuario);
		log.info("en el metodo add del controller");
		return "form";
	}

}
