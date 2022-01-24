package com.teamfaceless.facelessjobs.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.teamfaceless.facelessjobs.model.OfertaEmpleo;
import com.teamfaceless.facelessjobs.services.IOfertaService;

@Controller
public class PruebaFiltros {
	
	@Autowired
	private IOfertaService ofertaService;

	@GetMapping(value ="/test" )
	public String test(  
			@RequestParam(required = false)String titulo,
			@RequestParam(required = false)String descripcion,
			@RequestParam(required = false)String provincia,
			@RequestParam(required = false)String sector,
			@RequestParam(required = false)Integer salarioMinimo,
			@RequestParam(required = false)Integer salarioMaximo){
		List<OfertaEmpleo> lista=ofertaService.findByTituloAndDescripcion(
				titulo, descripcion, provincia, sector,salarioMinimo,salarioMaximo);
		
		
		return "redirect:/index";
	}
}
