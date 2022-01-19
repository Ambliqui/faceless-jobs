package com.teamfaceless.facelessjobs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamfaceless.facelessjobs.model.Candidato;
import com.teamfaceless.facelessjobs.model.InscripcionOferta;
import com.teamfaceless.facelessjobs.model.InscripcionOfertaPK;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;
import com.teamfaceless.facelessjobs.services.ICandidatoService;
import com.teamfaceless.facelessjobs.services.IInscriptionService;
import com.teamfaceless.facelessjobs.services.IOfertaService;
import com.teamfaceless.facelessjobs.validations.IValidations;

@Controller
@RequestMapping("/inscription")
public class InscriptionController { 
	
	@Autowired
	private IValidations iValidations;
	
	@Autowired
	private ICandidatoService candidatoService;
	
	@Autowired
	private IInscriptionService inscriptionService;
	
	@Autowired
	private IOfertaService ofertaService;
	
	@PostMapping("/save")
	public String saveInscription(OfertaEmpleo offert) {
	
		
		//TODO cambiar por usuario de session
		Candidato candidato = candidatoService.findById(1).get();
		//TODO recoger oferta de 
		//OfertaEmpleo offert = ofertaService.findById(offert.getIdOfertaEmpleo()).get();
		//TODO
		InscripcionOfertaPK keyInscription = new InscripcionOfertaPK(offert.getIdOfertaEmpleo(), candidato.getIdCandidato());
		InscripcionOferta inscription= InscripcionOferta.builder()
			.candidato(candidato)
			.ofertaEmpleo(offert)
			.inscripcionOfertaPK(keyInscription)
			.fechaInscripcion(null)
			.build();
			
		inscriptionService.save(inscription);
		return "/views/candidato/perfil";
	}

}
