package com.teamfaceless.facelessjobs.controller;

import java.util.Date;
import java.util.Map;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/inscription")
public class InscriptionController {

	@Autowired
	private ICandidatoService candidatoService;
	@Autowired
	private IOfertaService ofertaService;
	@Autowired
	private IInscriptionService inscriptionService;

	@PostMapping("/save")
	public String saveInscription(@ModelAttribute("oferta") OfertaEmpleo offert, Authentication auth, RedirectAttributes redirect) {

		String nombre = auth.getName();
		Candidato candidato = candidatoService.findByEmail(nombre).get();
		OfertaEmpleo oferta = ofertaService.findById(offert.getIdOfertaEmpleo()).get();
		
		// Validar que el usuario cumple los requisiatos requeridos
		Map<String, String> mapaErrores = inscriptionService.validadorInscripcion(oferta, candidato);
		if (!mapaErrores.isEmpty()) {
			redirect.addFlashAttribute("error", "No cumples los requisitos exigidos para esta oferta");
			return "redirect:/oferta/detalle/" + oferta.getIdOfertaEmpleo();
		} else {
			InscripcionOfertaPK keyInscription = new InscripcionOfertaPK(offert.getIdOfertaEmpleo(),candidato.getIdCandidato());
			InscripcionOferta inscription = InscripcionOferta.builder()
				.candidato(candidato)
				.ofertaEmpleo(oferta)
				.inscripcionOfertaPK(keyInscription)
				.fechaInscripcion(new Date())
				.build();
//			inscriptionService.create(inscription);
			return "redirect:/";
		}
	}

}
