package com.teamfaceless.facelessjobs.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teamfaceless.facelessjobs.model.Candidato;
import com.teamfaceless.facelessjobs.model.Empresa;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;
import com.teamfaceless.facelessjobs.model.Rol;
import com.teamfaceless.facelessjobs.services.ICandidatoService;
import com.teamfaceless.facelessjobs.services.IEmpresaService;
import com.teamfaceless.facelessjobs.services.IOfertaService;
import com.teamfaceless.facelessjobs.services.IProvinciaService;
import com.teamfaceless.facelessjobs.services.IRolService;
import com.teamfaceless.facelessjobs.services.ISectorService;
import com.teamfaceless.facelessjobs.validations.IValidations;

@Controller
@RequestMapping("/oferta")
public class ControllerOferta {
	@Autowired
	private IOfertaService ofertaService;
	@Autowired
	private IEmpresaService empresaService;
	@Autowired
	private ICandidatoService candidatoService;
	@Autowired
	private IProvinciaService provinciaService;
	@Autowired
	private ISectorService sectorService;
	@Autowired
	private IRolService rolService;
	@Autowired
	private IValidations iValidations;

	@GetMapping("/listado")
	public String goListado(Model model, Authentication auth) {
		String email = auth.getName();
		Rol rol = rolService.findByUser(email).get();
		if (rol.getNombre().equals("ROLE_EMPRESA")) {
			Empresa empresa = empresaService.findByEmailEmpresa(email).get();
			Integer id = empresa.getIdEmpresa();
			model.addAttribute("ofertas", ofertaService.findOfertaByEmpresa(id));
			model.addAttribute("titulo", "Mis ofertas publicadas:");

		} else if (rol.getNombre().equals("ROLE_CANDIDATO")) {
			Candidato candidato = candidatoService.findByEmail(email).get();
			Integer id = candidato.getIdCandidato();
			model.addAttribute("ofertas", ofertaService.findOfertaByidCandidato(id));
			model.addAttribute("titulo", "Mis inscripciones:");
		}
		return "views/oferta/listado";

	}

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
//				iValidations.inscripcionExistente(idOfertaEmpleo, idCandidato)
//					.ifPresent((error) -> mapaErrores.put("ErrorYaInscrito", error.getMessage()));
					model.addAttribute("msg", mapaErrores);
					model.addAttribute("error", "¡YA ESTAS INSCRITO/A A ESTA OFERTA!");
					model.addAttribute("btn", "hidden");
				} else {
					model.addAttribute("btn", "submit");
				}
			}
//			if (!iValidations.inscripcionExistente(idOfertaEmpleo, idCandidato).isPresent()) {
//				model.addAttribute("btn", "submit");
//			}
		}

		model.addAttribute("titulo", oferta.get().getTituloOferta());
		model.addAttribute("desc", "Descripción");
		model.addAttribute("descOferta", oferta.get().getDescripcionOferta());
		model.addAttribute("empresa", empresaService.findEmpresa(oferta.get()));
		model.addAttribute("idOferta", oferta.get().getIdOfertaEmpleo());
		model.addAttribute("salario", oferta.get().getSalarioOferta());
		model.addAttribute("provincia", oferta.get().getProvinciaOferta().getNombreProvincia());
		model.addAttribute("fechaPubli", oferta.get().getFechaInicioOferta());
		model.addAttribute("localidad", oferta.get().getLocalidadOferta());
		model.addAttribute("oferta", oferta);

		return "views/oferta/detalle";
	}

	@GetMapping(value = "/formulario")
	public String crearOferta(Model model) {
		LocalDate hoy=LocalDate.now();
		
		OfertaEmpleo oferta = new OfertaEmpleo();
		Optional<Empresa> emp = empresaService.findById(1);
		model.addAttribute("empresa", emp);
		model.addAttribute("titulo", "Alta de ofertas");
		model.addAttribute("value", "Añadir");
		model.addAttribute("provincias", provinciaService.findAll());
		model.addAttribute("sectores", sectorService.findAll());
		model.addAttribute("oferta", oferta);
		model.addAttribute("hoy",hoy);
		return "views/oferta/formulario";
	}

	@GetMapping(value = "/formularioModificar/{idOfertaEmpleo}")
	public String modificarOferta(@PathVariable(value = "idOfertaEmpleo") Integer idOfertaEmpleo, Model model) {
		Optional<OfertaEmpleo> oferta = null;
		if (idOfertaEmpleo > 0) {
			oferta = ofertaService.findById(idOfertaEmpleo);
		} else {
			return "redirect:oferta/listado";
		}
		model.addAttribute("provincias", provinciaService.findAll());
		model.addAttribute("sectores", sectorService.findAll());
		model.addAttribute("oferta", oferta);
		model.addAttribute("value", "Editar");
		model.addAttribute("titulo", "Editar ofertas");
		return "views/oferta/formularioModificar";
	}

	@PostMapping(value = "/guardar")
	public String guardarOferta(@Valid @ModelAttribute("oferta") OfertaEmpleo oferta, BindingResult result,
			Model model,Authentication auth) {
		LocalDate hoy=LocalDate.now();
		String email = auth.getName();
		Rol rol = rolService.findByUser(email).get();

		if (result.hasErrors() || oferta.getFechaFinOferta().isBefore(hoy)) {
			model.addAttribute("titulo", "Formulario de ofertas");
			model.addAttribute("value", "Añadir");
			model.addAttribute("provincias", provinciaService.findAll());
			model.addAttribute("sectores", sectorService.findAll());
			model.addAttribute("hoy",hoy);
			model.addAttribute("msgErrorFecha","La fecha de fin no puede ser anterior a la fecha de hoy.");
			return "views/oferta/formulario";
		} 
		
		if (rol.getNombre().equals("ROLE_EMPRESA")) {
			Empresa empresa = empresaService.findByEmailEmpresa(email).get();
			Integer id = empresa.getIdEmpresa();
			oferta.setEmpresa(empresaService.findById(id).get());
			
		}
	
		oferta.setFechaInicioOferta(hoy);
		ofertaService.create(oferta);
		System.out.println("Oferta añadida con exito.");
		return "redirect:listado";
	}
	@PostMapping(value = "/modificar")
	public String modificarOferta(@Valid @ModelAttribute("oferta") OfertaEmpleo oferta, BindingResult result,
			Model model,Authentication auth) {
		
		LocalDate hoy=oferta.getFechaInicioOferta();
		String email = auth.getName();
		Rol rol = rolService.findByUser(email).get();

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de ofertas");
			model.addAttribute("value", "Editar");
			model.addAttribute("provincias", provinciaService.findAll());
			model.addAttribute("sectores", sectorService.findAll());
			model.addAttribute("hoy",hoy);
			return "views/oferta/formularioModificar";
		} 
		
		if (rol.getNombre().equals("ROLE_EMPRESA")) {
			Empresa empresa = empresaService.findByEmailEmpresa(email).get();
			Integer id = empresa.getIdEmpresa();
			oferta.setEmpresa(empresaService.findById(id).get());
			
		}
		if(oferta.getFechaFinOferta().isBefore(hoy)) {
			model.addAttribute("titulo", "Formulario de ofertas");
			model.addAttribute("value", "Editar");
			model.addAttribute("provincias", provinciaService.findAll());
			model.addAttribute("sectores", sectorService.findAll());
			model.addAttribute("hoy",hoy);
			model.addAttribute("msgErrorFecha","La fecha de fin no puede ser anterior a la fecha de hoy.");
			return "views/oferta/formulario";
		}
		oferta.setFechaInicioOferta(hoy);
		ofertaService.create(oferta);
		System.out.println("Oferta añadida con exito.");
		return "redirect:listado";
	}
	

	@GetMapping(value = "/eliminar/{idOfertaEmpleo}")
	public String eliminarOferta(@PathVariable("idOfertaEmpleo") Integer idOfertaEmpleo) {
		ofertaService.delete(idOfertaEmpleo);
		System.out.println("Oferta eliminada con exito.");

		return "redirect:/oferta/listado";
	}

	@GetMapping(value = "/confirmar/{idOfertaEmpleo}")
	public String confirmarBorrado(@PathVariable("idOfertaEmpleo") Integer idOfertaEmpleo, Model model) {
		model.addAttribute("pregunta", "¿Estás seguro/a de eliminar esta oferta?");
		model.addAttribute("msg", "Una vez eliminada,¡no se prodrá restablecer!");
		model.addAttribute("id", idOfertaEmpleo);
		return "views/oferta/confirmar";
	}
}
