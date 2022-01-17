package com.teamfaceless.facelessjobs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/empresa")
public class ControllerEmpresaPrivado {

	@GetMapping("/home")
	public String home() {
		return "views/app/empresa/home";
	}
	
}
