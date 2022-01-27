package com.teamfaceless.facelessjobs.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamfaceless.facelessjobs.model.Candidato;
import com.teamfaceless.facelessjobs.model.Empresa;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;
import com.teamfaceless.facelessjobs.model.Rol;
import com.teamfaceless.facelessjobs.services.ICandidatoService;
import com.teamfaceless.facelessjobs.services.IEmpresaService;
import com.teamfaceless.facelessjobs.services.IOfertaService;
import com.teamfaceless.facelessjobs.services.IRolService;
import com.teamfaceless.facelessjobs.validations.IValidations;

@Controller
@RequestMapping("/oferta")
public class ControllerOferta2 {
	@Autowired
	private IOfertaService ofertaService;
	@Autowired
	private IRolService rolService;
	@Autowired
	private IValidations iValidations;
	@Autowired
	private IEmpresaService empresaService;
	@Autowired
	private ICandidatoService candidatoService;

	@GetMapping(value = "/detalle/{idOfertaEmpleo}")
	public String mostrarDetalle(@PathVariable(value = "idOfertaEmpleo") Integer idOfertaEmpleo, Model model,
			Authentication auth) {

		Optional<OfertaEmpleo> oferta = null;
		oferta = ofertaService.findById(idOfertaEmpleo);
		Map<String, String> mapaErrores = new HashMap<>();

		if (!Objects.isNull(auth)) {
			String email = auth.getName();
			Rol rol = rolService.findByUser(auth.getName()).get();
			if (rol.getNombre().equals("ROLE_CANDIDATO")) {
				Candidato candidato = candidatoService.findByEmail(email).get();
				Integer idCandidato = candidato.getIdCandidato();

				if (iValidations.inscripcionExistente(idOfertaEmpleo, idCandidato).isPresent()) {
					model.addAttribute("msg", mapaErrores);
					model.addAttribute("error", "¡YA ESTAS INSCRITO/A A ESTA OFERTA!");
					model.addAttribute("btn", "hidden");
				} else if (iValidations.inscripcionRequisitosNoCoincidentes(oferta.get(), candidato).isPresent()) {
					model.addAttribute("msg", mapaErrores);
					model.addAttribute("error", "No cumples los requisitos para inscribirte de esta oferta");
					model.addAttribute("btn", "hidden");
				} else {
					model.addAttribute("btn", "submit");
				}
			}
		}

		model.addAttribute("oferta", oferta.get());

		return "views/oferta/detalle";
	}

	@GetMapping("/listado")
	public String goListado(Model model, Authentication auth) {
		String email = auth.getName();
		Rol rol = rolService.findByUser(email).get();
		if (rol.getNombre().equals("ROLE_EMPRESA")) {
			Empresa empresa = empresaService.findByEmailEmpresa(email).get();
			model.addAttribute("ofertas", empresa.getOfertasEmpleos());
			model.addAttribute("titulo", "Mis ofertas publicadas");

		} else if (rol.getNombre().equals("ROLE_CANDIDATO")) {
			Candidato candidato = candidatoService.findByEmail(email).get();
			model.addAttribute("ofertas", ofertaService.findOfertaByidCandidato(candidato.getIdCandidato()));
			model.addAttribute("titulo", "Mis inscripciones");
		}
		return "views/oferta/listado";
	}
}
