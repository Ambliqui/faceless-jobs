package com.teamfaceless.facelessjobs.controller;

import java.util.Date;

import com.teamfaceless.facelessjobs.dtos.empresa.mapper.IEmpresaMapper;
import com.teamfaceless.facelessjobs.model.Candidato;
import com.teamfaceless.facelessjobs.model.InscripcionOferta;
import com.teamfaceless.facelessjobs.model.InscripcionOfertaPK;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;
import com.teamfaceless.facelessjobs.services.ICandidatoService;
import com.teamfaceless.facelessjobs.services.IEmpresaService;
import com.teamfaceless.facelessjobs.services.IInscriptionService;
import com.teamfaceless.facelessjobs.services.IOfertaService;
import com.teamfaceless.facelessjobs.services.IProvinciaService;
import com.teamfaceless.facelessjobs.services.ISectorService;
import com.teamfaceless.facelessjobs.validations.IValidations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inscription")
public class InscriptionController { 
	
	@Autowired
	private ICandidatoService candidatoService;
	
	@Autowired
	private IInscriptionService inscriptionService;
	
	@Autowired
	private IOfertaService ofertaService;
	
	@PostMapping("/save")
	public String saveInscription(@ModelAttribute("oferta") OfertaEmpleo offert, Authentication auth) {
	
		
		String nombre = auth.getName();
		Candidato candidato = candidatoService.findByEmail(nombre).get();
		OfertaEmpleo oferta = ofertaService.findById(offert.getIdOfertaEmpleo()).get();
		//TODO Validar Que el usuario cumple los requisiatos requeridos
		
		InscripcionOfertaPK keyInscription = new InscripcionOfertaPK(offert.getIdOfertaEmpleo(), candidato.getIdCandidato());
		InscripcionOferta inscription= InscripcionOferta.builder()
			.candidato(candidato)
			.ofertaEmpleo(oferta)
			.inscripcionOfertaPK(keyInscription)
			.fechaInscripcion(new Date())
			.build();
			
		inscriptionService.create(inscription);
		//return "/views/app/candidato/perfil/";
		return "redirect:/";
	}

}
