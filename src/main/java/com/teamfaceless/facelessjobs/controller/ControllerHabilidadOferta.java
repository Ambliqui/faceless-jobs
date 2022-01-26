package com.teamfaceless.facelessjobs.controller;

import java.util.ArrayList;
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
	
	private int limiteHabilidades = 5;
	
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
		
		List<HabilidadOferta> habilidadesDurasAnadidas = habOfeService.findHabilidadesOfertaDurasByOferta(ofertaEmpleo);
		model.addAttribute("habilidadesDurasAnadidas", habilidadesDurasAnadidas);
		List<HabilidadOferta> habilidadesBlandasAnadidas = habOfeService.findHabilidadesOfertaBlandasByOferta(ofertaEmpleo);
		model.addAttribute("habilidadesBlandasAnadidas", habilidadesBlandasAnadidas);
//		model.addAttribute("habilidadesAnadidasEnLaOferta", ofertaEmpleo.getHabilidadOfertaList());
		
		boolean isMaxDuras = habilidadesDurasAnadidas.size()>=limiteHabilidades;
		boolean isMaxBlandas = habilidadesBlandasAnadidas.size()>=limiteHabilidades;
		boolean isAllowedToAdd = true;
		
		List<Habilidad> listaHabilidadesDurasRestante = new ArrayList<>();
		List<Habilidad> listaHabilidadesBlandasRestante = new ArrayList<>();
		
		String errorMsg = "";
		int errorType = 0;
		
		if(isMaxBlandas&&isMaxDuras) {
			errorMsg="Máximo alcanzado, no se pueden añadir más de " + limiteHabilidades + " habilidades en cada categoría";
			isAllowedToAdd=false;
			errorType=2;
		}
		else {
			if(!isMaxDuras) {
				listaHabilidadesDurasRestante=habOfeService.findHabilidadesDurasRestantesByOferta(ofertaEmpleo);
			}
			else {
				errorMsg="Máximo alcanzado, no se pueden añadir más de " + limiteHabilidades + " habilidades duras";
				errorType=1;
			}
			if(!isMaxBlandas) {
					listaHabilidadesBlandasRestante=habOfeService.findHabilidadesBlandasRestantesByOferta(ofertaEmpleo);
			}
			else {
				errorMsg="Máximo alcanzado, no se pueden añadir más de " + limiteHabilidades + " habilidades blandas";
				errorType=1;
			}
		}
		
		model.addAttribute("listaHabilidadesBlandasRestante", listaHabilidadesBlandasRestante);
		model.addAttribute("listaHabilidadesDurasRestante", listaHabilidadesDurasRestante);
		model.addAttribute("errorMsg", errorMsg);
		model.addAttribute("isAllowedToAdd", isAllowedToAdd);
		model.addAttribute("errorType", errorType);
//		model.addAttribute("listaHabilidadesRestante", habOfeService.findHabilidadesRestantesByOferta(ofertaEmpleo));
		
		return "views/app/empresa/oferta/formularioAdd";
	}
	@PostMapping("/{idOferta}")
	public String goListadoPost(@PathVariable Integer idOferta,Model model) {
		return "redirect: /"+idOferta;
	}
	
	
	@PostMapping("/guardar")
	public String altaHabilidadOferta(@Valid HabilidadOferta habilidadOferta, BindingResult result, Model model,String isObligatorio) {
		if(result.hasErrors()) {
				return "redirect:/habilidadOferta/"+habilidadOferta.getOfertaEmpleo().getIdOfertaEmpleo();
			}
		List<HabilidadOferta> habilidadesDurasAnadidas = habOfeService.findHabilidadesOfertaDurasByOferta(habilidadOferta.getOfertaEmpleo());
		List<HabilidadOferta> habilidadesBlandasAnadidas = habOfeService.findHabilidadesOfertaBlandasByOferta(habilidadOferta.getOfertaEmpleo());
		if(habilidadesDurasAnadidas.size()>=limiteHabilidades&&habilidadesBlandasAnadidas.size()>=limiteHabilidades) {
			return "redirect:/habilidadOferta/"+habilidadOferta.getOfertaEmpleo().getIdOfertaEmpleo();
		}
		
		habilidadOferta.setHabilidadOfertaPK(new HabilidadOfertaPK(habilidadOferta.getOfertaEmpleo().getIdOfertaEmpleo(), habilidadOferta.getHabilidad().getIdHabilidad()));
		
		habilidadOferta.setObligatorio(Boolean.valueOf(isObligatorio));
		habOfeService.modify(habilidadOferta);
		return "redirect:/habilidadOferta/"+habilidadOferta.getOfertaEmpleo().getIdOfertaEmpleo();
	}
	
	@GetMapping("/modificar/{idHabilidad}/{idOferta}")
	public String modificarHabilidadOferta(@PathVariable Integer idHabilidad,@PathVariable Integer idOferta, Model model) {
		
		OfertaEmpleo ofertaEmpleo = ofeService.findById(idOferta).get();
		model.addAttribute("ofertaEmpleo", ofertaEmpleo);
		
		Habilidad habilidad = habService.findById(idHabilidad).get();
		model.addAttribute("thisHabilidad", habilidad);
		
		HabilidadOferta thisHabilidadOferta = habOfeService.findHabilidadOfertaByOfertaAndHabilidad(ofertaEmpleo, habilidad);
		model.addAttribute("thisExperiencia", thisHabilidadOferta.getExperienciaOferta());
		model.addAttribute("thisIsObligatorio", thisHabilidadOferta.isObligatorio());
		model.addAttribute("thisBaremo",thisHabilidadOferta.getBaremo());
		
		model.addAttribute("habilidadOferta", new HabilidadOferta());
		
		model.addAttribute("habilidadesDurasAnadidas", habOfeService.findHabilidadesOfertaDurasByOferta(ofertaEmpleo));
		model.addAttribute("habilidadesBlandasAnadidas", habOfeService.findHabilidadesOfertaBlandasByOferta(ofertaEmpleo));
//		model.addAttribute("habilidadesAnadidasEnLaOferta", ofertaEmpleo.getHabilidadOfertaList());
		
		return "views/app/empresa/oferta/formularioModificar";
	}
	
	@PostMapping("/modificarConfirmado")
	public String modificarHabilidadOfertaConfirmado(@Valid HabilidadOferta habilidadOferta, BindingResult result, Model model,String isObligatorio) {
		if(result.hasErrors()) {
			return "redirect:/habilidadOferta/"+habilidadOferta.getOfertaEmpleo().getIdOfertaEmpleo();
		}
		habilidadOferta.setHabilidadOfertaPK(new HabilidadOfertaPK(habilidadOferta.getOfertaEmpleo().getIdOfertaEmpleo(), habilidadOferta.getHabilidad().getIdHabilidad()));
		
		habilidadOferta.setObligatorio(Boolean.valueOf(isObligatorio));
		habOfeService.modify(habilidadOferta);
		return "redirect:/habilidadOferta/"+habilidadOferta.getOfertaEmpleo().getIdOfertaEmpleo();
	}
	
	@GetMapping("/eliminar/{idHabilidad}/{idOferta}")
	public String eliminarHabilidadOferta(@PathVariable Integer idHabilidad,@PathVariable Integer idOferta, Model model) {
		OfertaEmpleo ofertaEmpleo = ofeService.findById(idOferta).get();
		Habilidad habilidad = habService.findById(idHabilidad).get();
		
		HabilidadOferta habilidadOferta = habOfeService.findHabilidadOfertaByOfertaAndHabilidad(ofertaEmpleo, habilidad);
		
		habOfeService.delete(habilidadOferta);
		
		return "redirect:/habilidadOferta/"+idOferta;
	}
}
