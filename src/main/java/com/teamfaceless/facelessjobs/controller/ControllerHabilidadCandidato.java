package com.teamfaceless.facelessjobs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamfaceless.facelessjobs.model.Candidato;
import com.teamfaceless.facelessjobs.model.Habilidad;
import com.teamfaceless.facelessjobs.model.HabilidadCandidato;
import com.teamfaceless.facelessjobs.model.HabilidadCandidatoPK;
import com.teamfaceless.facelessjobs.services.ICandidatoService;
import com.teamfaceless.facelessjobs.services.IHabilidadCandidatoService;
import com.teamfaceless.facelessjobs.services.IHabilidadService;

@Controller
@RequestMapping("/habilidadCandidato")
public class ControllerHabilidadCandidato {

	@Autowired
	private IHabilidadService habService;
	
	@Autowired
	private ICandidatoService canService;
	
	@Autowired
	private IHabilidadCandidatoService habCanService;
	
	@GetMapping("/{idCandidato}")
	public String goListadoGet(@PathVariable Integer idCandidato,Model model) {
		
		Candidato candidato = canService.findById(idCandidato);
		model.addAttribute("candidato", candidato);
		
		model.addAttribute("habilidadCandidato", new HabilidadCandidato());
		
		model.addAttribute("habilidadesDurasAnadidas", habCanService.findHabilidadesCandidatoDurasByCandidato(candidato));
		model.addAttribute("habilidadesBlandasAnadidas", habCanService.findHabilidadesCandidatoBlandasByCandidato(candidato));
		
		model.addAttribute("listaHabilidadesBlandasRestante", habCanService.findHabilidadesBlandasRestantesByCandidato(candidato));
		model.addAttribute("listaHabilidadesDurasRestante", habCanService.findHabilidadesDurasRestantesByCandidato(candidato));
		
		return "views/app/candidato/habilidades/formularioAdd";
	}
	@PostMapping("/{idOferta}")
	public String goListadoPost(@PathVariable Integer idCandidato,Model model) {
		return "redirect: /"+idCandidato;
	}
	
	@PostMapping("/guardar")
	public String altaHabilidadCandidato(@Valid HabilidadCandidato habilidadCandidato, BindingResult result, Model model,String isDemostrable) {
		if(result.hasErrors()) {
				return "redirect:/habilidadCandidato/"+habilidadCandidato.getCandidato().getIdCandidato();
			}
		habilidadCandidato.setHabilidadCandidatoPK(new HabilidadCandidatoPK(habilidadCandidato.getCandidato().getIdCandidato(), habilidadCandidato.getHabilidad().getIdHabilidad()));
		
		Integer nota = (int) (100 - (Math.random()*10)*(Math.random()*10));
		habilidadCandidato.setNotaHabilidadCandidato(nota);
		
		habilidadCandidato.setDemostrable(Boolean.valueOf(isDemostrable));
		habCanService.modify(habilidadCandidato);
		return "redirect:/habilidadCandidato/"+habilidadCandidato.getCandidato().getIdCandidato();
	}
	
	@GetMapping("/modificar/{idHabilidad}/{idCandidato}")
	public String modificarHabilidadOferta(@PathVariable Integer idHabilidad,@PathVariable Integer idCandidato, Model model) {
		
		Candidato candidato = canService.findById(idCandidato);
		model.addAttribute("candidato", candidato);
		
		Habilidad habilidad = habService.findById(idHabilidad).get();
		model.addAttribute("thisHabilidad", habilidad);
		
		HabilidadCandidato thisHabilidadCandidato = habCanService.findHabilidadCandidatoByCandidatoAndHabilidad(candidato, habilidad);
		model.addAttribute("thisExperiencia", thisHabilidadCandidato.getExperienciaCandidato());
		model.addAttribute("thisIsDemostrable", thisHabilidadCandidato.isDemostrable());
		model.addAttribute("thisNota",thisHabilidadCandidato.getNotaHabilidadCandidato());
		
		model.addAttribute("habilidadCandidato", new HabilidadCandidato());
		
		model.addAttribute("habilidadesDurasAnadidas", habCanService.findHabilidadesCandidatoDurasByCandidato(candidato));
		model.addAttribute("habilidadesBlandasAnadidas", habCanService.findHabilidadesCandidatoBlandasByCandidato(candidato));
		
		return "views/app/candidato/habilidades/formularioModificar";
	}
	
	@PostMapping("/modificarConfirmado")
	public String modificarHabilidadCandidatoConfirmado(@Valid HabilidadCandidato habilidadCandidato, BindingResult result, Model model,String isDemostrable) {
		if(result.hasErrors()) {
			return "redirect:/habilidadCandidato/"+habilidadCandidato.getCandidato().getIdCandidato();
		}
		habilidadCandidato.setHabilidadCandidatoPK(new HabilidadCandidatoPK(habilidadCandidato.getCandidato().getIdCandidato(), habilidadCandidato.getHabilidad().getIdHabilidad()));
		
		habilidadCandidato.setDemostrable(Boolean.valueOf(isDemostrable));
		habCanService.modify(habilidadCandidato);
		return "redirect:/habilidadCandidato/"+habilidadCandidato.getCandidato().getIdCandidato();
	}
	
	@GetMapping("/eliminar/{idHabilidad}/{idCandidato}")
	public String eliminarHabilidadCandidato(@PathVariable Integer idHabilidad,@PathVariable Integer idCandidato, Model model) {
		Candidato candidato = canService.findById(idCandidato);
		Habilidad habilidad = habService.findById(idHabilidad).get();
		
		HabilidadCandidato habilidadCandidato = habCanService.findHabilidadCandidatoByCandidatoAndHabilidad(candidato, habilidad);
		
		habCanService.delete(habilidadCandidato);
		
		return "redirect:/habilidadCandidato/"+idCandidato;
	}
}
