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
	
	@GetMapping("/401")
	public String goError401() {
		return "views/error/401";
	}
	
	@GetMapping("/403")
	public String goError403() {
		return "views/error/403";
	}
	
	@GetMapping("/404")
	public String goError404() {
		return "views/error/404";
	}
	
	@GetMapping("/502")
	public String goError502() {
		return "views/error/502";
	}
	
	@GetMapping("/503")
	public String goError503() {
		return "views/error/503";
	}
	
	@GetMapping("/504")
	public String goError504() {
		return "views/error/504";
	}
	
	@GetMapping("/505")
	public String goError505() {
		return "views/error/505";
	}
	
	@GetMapping("/507")
	public String goError507() {
		return "views/error/507";
	}
	
	@GetMapping("/509")
	public String goError509() {
		return "views/error/509";
	}
	
	@GetMapping("/50X")
	public String goError50X() {
		return "views/error/50X";
	}
	
	@GetMapping("/510")
	public String goError510() {
		return "views/error/510";
	}
	
	@GetMapping({"/x","/X"})
	public String goErrorUnknown() {
		return "views/error/general";
	}
}
