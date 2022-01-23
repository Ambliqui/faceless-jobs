package com.teamfaceless.facelessjobs.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamfaceless.facelessjobs.dtos.empresa.mapper.IEmpresaMapper;
import com.teamfaceless.facelessjobs.model.Candidato;
import com.teamfaceless.facelessjobs.model.Empresa;
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

@Controller
@RequestMapping("/inscription")
public class InscriptionController { 

	@Autowired
	private IEmpresaService iEmpresaService;

	@Autowired
	private IProvinciaService iProvinciaService;
	
	@Autowired
	private ISectorService iSectorService;
	
	@Autowired
	private IEmpresaMapper iEmpresaMapper;
	
	@Autowired
	private IValidations iValidations;
	
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
		//TODO recoger oferta de 
		OfertaEmpleo oferta = ofertaService.findById(offert.getIdOfertaEmpleo()).get();
		InscripcionOfertaPK keyInscription = new InscripcionOfertaPK(offert.getIdOfertaEmpleo(), candidato.getIdCandidato());
		InscripcionOferta inscription= InscripcionOferta.builder()
			.candidato(candidato)
			.ofertaEmpleo(oferta)
			.inscripcionOfertaPK(keyInscription)
			.fechaInscripcion(new Date())
			.build();
			
		inscriptionService.create(inscription);
		return "/views/app/candidato/perfil";
	}

}
