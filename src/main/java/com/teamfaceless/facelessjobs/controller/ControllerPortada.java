package com.teamfaceless.facelessjobs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("portada")
@RequestMapping("/portada")
public class ControllerPortada {
	
	@RequestMapping(value={"","/"},method = {RequestMethod.POST,RequestMethod.GET} )
	public String getPortada() {
		return "/views/generic/portada";
	}

}
