package com.teamfaceless.facelessjobs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/candidato")
public class IsiClienteController {

	@GetMapping("/perfil")
	public String goPerfil(Model model) {
		model.addAttribute("titulo", "Perfil de usuario");
		return "views/candidato/perfil";
	}
}
