package com.teamfaceless.facelessjobs.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.teamfaceless.facelessjobs.dtos.candidato.CandidatoModifyDto;
import com.teamfaceless.facelessjobs.dtos.candidato.CandidatoRegistroDto;
import com.teamfaceless.facelessjobs.dtos.candidato.mapper.ICandidatoMapper;
import com.teamfaceless.facelessjobs.exceptions.EmailExisteException;
import com.teamfaceless.facelessjobs.model.Candidato;
import com.teamfaceless.facelessjobs.model.Credencial;
import com.teamfaceless.facelessjobs.services.ICandidatoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/candidato")
public class ControllerCandidato {

	@Autowired
	private ICandidatoService candidatoService;
	@Autowired
	private ICandidatoMapper candidatoMapper;

	@Autowired
	private HttpSession httpSession;

	@GetMapping("/registro")
	public String formRegistro(Model model, CandidatoRegistroDto candidatoRegistroDto) {
		model.addAttribute("candidato", candidatoRegistroDto);
		return "views/candidato/registro";
	}

	@PostMapping("/registro")
	public String registrar(@Valid @ModelAttribute("candidato") CandidatoRegistroDto candidatoRegistroDto,
			BindingResult result, Model model, RedirectAttributes redirect) {

		if (result.hasErrors()) {
			System.out.println("HAY ERRORES");
			return "views/candidato/registro";
		}
		// validacion pass coinciden y email coincide
		if (!candidatoRegistroDto.emailsEquals()) {
			model.addAttribute("errorEmail", "Los email no coinciden");// Preparar mensaje para internacionalizar
			return "views/candidato/registro";
		}
		if (!candidatoRegistroDto.passEquals()) {
			model.addAttribute("errorPass", "Las contraseñas no coinciden");// Preparar mensaje para internacionalizar
			return "views/candidato/registro";
		}

		// registrar
		System.out.println("NO HAY ERRORES");
		Candidato candidato = candidatoMapper.candidatoRegistroDtoToCandidato(candidatoRegistroDto);
		// encriptar pass con security
		try {
			candidatoService.create(candidato);
			redirect.addFlashAttribute("msg", "Registrado correctamente");// Preparar mensaje para internacionalizar
			return "redirect:/candidato/login";
		} catch (EmailExisteException e) {
			model.addAttribute("error", e.getMessage());// Preparar mensaje para internacionalizar
			return "views/candidato/registro";
		}

	}

	@GetMapping("/perfil")
	public String goToCandidateProfile() {
		return "views/app/candidato/perfil";
	}

	@GetMapping("/modify")
	public String goToCandidateModify(Model model, CandidatoModifyDto candidatoModifyDto) {

		model.addAttribute("sessionCandidato", httpSession.getAttribute("userSession"));

		return "views/app/candidato/modify";
	}

	@PostMapping("/modify")
	public String candidateModifyData(@Valid @ModelAttribute("candidato") CandidatoModifyDto candidatoModifyDto,
			BindingResult result, RedirectAttributes redirect, Model model) {

		if (result.hasErrors()) {
			System.out.println("HAY ERRORES");
			model.addAttribute("sessionCandidato", candidatoModifyDto);
			return "views/app/candidato/modify";
		}

		Candidato candidatoTemp = (Candidato) httpSession.getAttribute("userSession");
		Credencial credencial = candidatoTemp.getCredencial();
		candidatoModifyDto.setCredencial(credencial);
		candidatoModifyDto.setIdCandidato(candidatoTemp.getIdCandidato());

		Candidato candidato = candidatoMapper.candidatoModifyDtoToCandidato(candidatoModifyDto);
		candidatoService.update(candidato);

		httpSession.setAttribute("userSession", candidato);
		redirect.addFlashAttribute("msg", "Modificado correctamente");
		return "redirect:/candidato/perfil";

	}

}
