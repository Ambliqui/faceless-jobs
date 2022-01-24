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
		
		HabilidadOferta habilidadOferta = new HabilidadOferta();
		habilidadOferta.setOfertaEmpleo(ofertaEmpleo);
//		habilidadOferta.setExperienciaOferta(3);
//		habilidadOferta.setHabilidad(habService.findById(1).get());
		model.addAttribute("habilidadOferta", habilidadOferta);
		
		List<Habilidad> listaCompleta = habService.findAll();
		List<Habilidad> listaHabilidadEnOferta = habOfeService.findHabilidadesByOfertaEmpleo(ofertaEmpleo);
		listaCompleta.removeAll(listaHabilidadEnOferta);
		model.addAttribute("habilidadesAnadidasEnLaOferta", ofertaEmpleo.getHabilidadOfertaList());
		model.addAttribute("listaHabilidadesRestante", listaCompleta);
		
		return "views/pruebaHabilidadOferta/formulario";
	}
	
	@PostMapping("/guardar")
	public String altaHabilidadOferta(@Valid HabilidadOferta habilidadOferta, BindingResult result, Model model) {
		if(result.hasErrors()) {
				return "redirect:/habilidadOferta/"+habilidadOferta.getOfertaEmpleo().getIdOfertaEmpleo();
			}
		habilidadOferta.setHabilidadOfertaPK(new HabilidadOfertaPK(habilidadOferta.getOfertaEmpleo().getIdOfertaEmpleo(), habilidadOferta.getHabilidad().getIdHabilidad()));
		//TODO
		habOfeService.modify(habilidadOferta);
		return "redirect:/habilidadOferta/formulario"+habilidadOferta.getOfertaEmpleo().getIdOfertaEmpleo();
	}
}
