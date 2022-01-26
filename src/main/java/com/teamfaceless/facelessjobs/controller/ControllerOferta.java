package com.teamfaceless.facelessjobs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.time.LocalDate;
import java.util.Optional;

import javax.servlet.http.HttpSession;
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
@RequestMapping("/app/empresa/oferta")
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
	@Autowired
	private HttpSession httpSession;

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
	
	@PostMapping("/listado")
	public String goListadoPost(Model model, Authentication auth) {
		return "redirect:/oferta/listado";
	}
	
	@GetMapping(value = "/detalle/{idOfertaEmpleo}")
	public String mostrarDetalle(@PathVariable(value = "idOfertaEmpleo") Integer idOfertaEmpleo, Model model,
			Authentication auth) {

		Optional<OfertaEmpleo> oferta = null;
		oferta =ofertaService.findById(idOfertaEmpleo);
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

		model.addAttribute("oferta", oferta.get());

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
		return "views/app/empresa/oferta/formulario";
	}

	@GetMapping(value = "/formularioModificar/{idOfertaEmpleo}")
	public String modificarOferta(@PathVariable(value = "idOfertaEmpleo") Integer idOfertaEmpleo, Model model) {
		Optional<OfertaEmpleo> oferta = null;
		if (idOfertaEmpleo > 0) {
			oferta = ofertaService.findById(idOfertaEmpleo);
		} else {
			return "redirect:/app/empresa/oferta/listado";
		}
		model.addAttribute("provincias", provinciaService.findAll());
		model.addAttribute("sectores", sectorService.findAll());
		model.addAttribute("oferta", oferta.get());
		model.addAttribute("value", "Editar");
		model.addAttribute("titulo", "Editar ofertas");
		return "views/app/empresa/oferta/formularioModificarOferta";
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
			return "views/app/empresa/oferta/formulario";
		} 
		
		if (rol.getNombre().equals("ROLE_EMPRESA")) {
			Empresa empresa = empresaService.findByEmailEmpresa(email).get();
			Integer id = empresa.getIdEmpresa();
			oferta.setEmpresa(empresaService.findById(id).get());
			
		}
	
		oferta.setFechaInicioOferta(hoy);
		ofertaService.create(oferta);
		Empresa empresaTemp = (Empresa) httpSession.getAttribute("userSession");
		empresaTemp.addOfertaEmpleo(oferta);
		httpSession.setAttribute("userSession", empresaTemp);
		System.out.println("Oferta añadida con exito.");
		return "redirect:/app/empresa/oferta/listado";
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
			return "views/app/empresa/oferta/formularioModificarOferta";
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
			return "views/app/empresa/oferta/formularioModificarOferta";
		}

		oferta.setFechaInicioOferta(hoy);
		ofertaService.create(oferta);
		
		//Modificar la lista de Ofertas de la empresa de session
		Empresa empresaTemp = (Empresa) httpSession.getAttribute("userSession");
		List<OfertaEmpleo> ofertasActuales = ofertaService.findOfertaByEmpresa(empresaTemp.getIdEmpresa());
		empresaTemp.setOfertasEmpleos(ofertasActuales);
		httpSession.setAttribute("userSession", empresaTemp);
		
		System.out.println("Oferta añadida con exito.");
		return "redirect:/app/empresa/oferta/listado";
	}
	

	@GetMapping(value = "/eliminar/{idOfertaEmpleo}")
	public String eliminarOferta(@PathVariable("idOfertaEmpleo") Integer idOfertaEmpleo) {
		ofertaService.delete(idOfertaEmpleo);
		System.out.println("Oferta eliminada con exito.");

		return "redirect:/app/empresa/oferta/listado";
	}

	@GetMapping(value = "/confirmar/{idOfertaEmpleo}")
	public String confirmarBorrado(@PathVariable("idOfertaEmpleo") Integer idOfertaEmpleo, Model model) {
		model.addAttribute("pregunta", "¿Estás seguro/a de eliminar esta oferta?");
		model.addAttribute("msg", "Una vez eliminada,¡no se prodrá restablecer!");
		model.addAttribute("id", idOfertaEmpleo);
		return "views/app/empresa/oferta/confirmar";
	}
}
