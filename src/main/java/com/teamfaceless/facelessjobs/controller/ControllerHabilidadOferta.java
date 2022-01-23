package com.teamfaceless.facelessjobs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamfaceless.facelessjobs.services.IHabilidadOfertaService;
import com.teamfaceless.facelessjobs.services.IHabilidadService;
import com.teamfaceless.facelessjobs.services.IOfertaService;

@Controller
@RequestMapping("/habilidadOferta")
public class ControllerHabilidadOferta {

	@Autowired
	private IHabilidadService habService;
	
	@Autowired
	private IOfertaService ofeService;
	
	@Autowired
	private IHabilidadOfertaService habOfeService;
	
	@GetMapping("")
	public String goListado(Model model) {
		model.addAttribute("habilidadesAnadidas", habOfeService.obtenerHabilidadesByOferta(ofeService.findById(18).get()));
		model.addAttribute("oferta", ofeService.findById(18).get());
		return "views/pruebaHabilidadOferta/formulario";
	}
}
