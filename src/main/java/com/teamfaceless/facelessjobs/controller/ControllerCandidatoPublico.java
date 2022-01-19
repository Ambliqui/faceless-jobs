package com.teamfaceless.facelessjobs.controller;

import javax.validation.Valid;

import com.teamfaceless.facelessjobs.dao.dtos.candidato.CandidatoLoginDto;
import com.teamfaceless.facelessjobs.dao.dtos.candidato.CandidatoRegistroDto;
import com.teamfaceless.facelessjobs.dao.dtos.candidato.mapper.ICandidatoMapper;
import com.teamfaceless.facelessjobs.enums.Provincias;
import com.teamfaceless.facelessjobs.exceptions.EmailExisteException;
import com.teamfaceless.facelessjobs.model.Candidato;
import com.teamfaceless.facelessjobs.services.ICandidatoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/candidato")
public class ControllerCandidatoPublico {
	
	@Autowired
	private ICandidatoService candidatoService;
	@Autowired
	private ICandidatoMapper candidatoMapper;
	
	@GetMapping("/registro")
	public String formRegistro(Model model,CandidatoRegistroDto candidatoRegistroDto) {
			model.addAttribute("candidato", candidatoRegistroDto);
			model.addAttribute("provincias", Provincias.values());
		return "views/candidato/registro";
	}
	
	@PostMapping("/registro")
	public String registrar(@Valid @ModelAttribute("candidato")CandidatoRegistroDto candidatoRegistroDto, 
			BindingResult result, Model model, RedirectAttributes redirect) {
			if(result.hasErrors()) {
				System.out.println("HAY ERRORES");
				model.addAttribute("provincias", Provincias.values());
				
				return "views/candidato/registro";
			}
			//validacion pass coinciden y email coincide
			if(!candidatoRegistroDto.emailsEquals()) {
				model.addAttribute("errorEmail", "Los email no coinciden");//Preparar mensaje para internacionalizar
				model.addAttribute("provincias", Provincias.values());
				
				return "views/candidato/registro";
			}
			if(!candidatoRegistroDto.passEquals()) {
				model.addAttribute("errorPass", "Las contraseñas no coinciden");//Preparar mensaje para internacionalizar
				model.addAttribute("provincias", Provincias.values());
				
				return "views/candidato/registro";
			}
			
		//registrar
			System.out.println("NO HAY ERRORES");
			Candidato candidato=candidatoMapper.candidatoRegistroDtoToCandidato(candidatoRegistroDto);
			
			try {
				candidatoService.create(candidato);
				redirect.addFlashAttribute("msg", "Registrado correctamente");//Preparar mensaje para internacionalizar
				return"redirect:/candidato/login";
			}catch(EmailExisteException e) {
				model.addAttribute("error", e.getMessage());//Preparar mensaje para internacionalizar
				return "views/candidato/registro";
			}
			
	}
	
	@GetMapping("/login")
	public String formLogin(Model model,@RequestParam(value = "error",required = false)String error) {
		if(error!=null) {
			model.addAttribute("msgError", "Credenciales incorrectas");
		}
		
		return"views/candidato/login";
	}
	
	@PostMapping("/login")
	public String login(Model model, @Valid @ModelAttribute("candidatoLogin") CandidatoLoginDto candidato,
			BindingResult result) {
		if(result.hasErrors()) {
			return"views/candidato/login";
		}
		
		//loguear
		return"views/app/candidato/home";
	}

}
