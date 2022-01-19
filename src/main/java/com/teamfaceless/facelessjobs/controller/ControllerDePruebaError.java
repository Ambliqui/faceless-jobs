package com.teamfaceless.facelessjobs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ControllerDePruebaError {

	@GetMapping("/400")
	public String goError400() {
		return "views/error/400";
	}
}
