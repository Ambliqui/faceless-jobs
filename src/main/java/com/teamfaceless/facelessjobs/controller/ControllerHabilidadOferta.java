package com.teamfaceless.facelessjobs.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamfaceless.facelessjobs.model.Habilidad;
import com.teamfaceless.facelessjobs.model.HabilidadOferta;
import com.teamfaceless.facelessjobs.model.HabilidadOfertaPK;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;
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
	
	@GetMapping("/{idOferta}")
	public String goListado(@PathVariable Integer idOferta,Model model) {
		
		OfertaEmpleo ofertaEmpleo = ofeService.findById(idOferta).get();
		model.addAttribute("ofertaEmpleo", ofertaEmpleo);
		
		model.addAttribute("habilidadOferta", new HabilidadOferta());
		
		model.addAttribute("habilidadesAnadidasEnLaOferta", ofertaEmpleo.getHabilidadOfertaList());
		model.addAttribute("listaHabilidadesRestante", habOfeService.findHabilidadesRestantesByOferta(ofertaEmpleo));
		
		return "views/app/empresa/oferta/formularioAdd";
	}
	@PostMapping("/{idOferta}")
	public String goListadoPost(@PathVariable Integer idOferta,Model model) {
		return "redirect: /"+idOferta;
	}
	
	
	@PostMapping("/guardar")
	public String altaHabilidadOferta(@Valid HabilidadOferta habilidadOferta, BindingResult result, Model model) {
		if(result.hasErrors()) {
				return "redirect:/habilidadOferta/"+habilidadOferta.getOfertaEmpleo().getIdOfertaEmpleo();
			}
		habilidadOferta.setHabilidadOfertaPK(new HabilidadOfertaPK(habilidadOferta.getOfertaEmpleo().getIdOfertaEmpleo(), habilidadOferta.getHabilidad().getIdHabilidad()));
		//TODO
		habOfeService.modify(habilidadOferta);
		return "redirect:/habilidadOferta/"+habilidadOferta.getOfertaEmpleo().getIdOfertaEmpleo();
	}
	
	@GetMapping("/modificar/{idHabilidad}/{idOferta}")
	public String modificarHabilidadOferta(@PathVariable Integer idHabilidad,@PathVariable Integer idOferta, Model model) {
		
		OfertaEmpleo ofertaEmpleo = ofeService.findById(idOferta).get();
		model.addAttribute("ofertaEmpleo", ofertaEmpleo);
		
		Habilidad habilidad = habService.findById(idHabilidad).get();
		model.addAttribute("thisHabilidad", habilidad);
		
		model.addAttribute("habilidadOferta", new HabilidadOferta());
		
		model.addAttribute("habilidadesAnadidasEnLaOferta", ofertaEmpleo.getHabilidadOfertaList());
		
		return "views/app/empresa/oferta/formularioModificar";
	}
}
