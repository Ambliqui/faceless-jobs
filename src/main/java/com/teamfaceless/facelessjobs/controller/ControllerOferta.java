package com.teamfaceless.facelessjobs.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamfaceless.facelessjobs.enums.Provincias;
import com.teamfaceless.facelessjobs.model.Empresa;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;
import com.teamfaceless.facelessjobs.services.IEmpresaService;
import com.teamfaceless.facelessjobs.services.IOfertaService;
import com.teamfaceless.facelessjobs.services.ISectorService;

@Controller
@RequestMapping("/oferta")
public class ControllerOferta {
	@Autowired
	private IOfertaService ofertaService;
	@Autowired
	private IEmpresaService empresaService;
	@Autowired
	private ISectorService sectorService;

	@GetMapping("/listado")
	public String goListado(Model model, @Param("idEmpresa") Integer idEmpresa) {
		idEmpresa = 1;
		model.addAttribute("ofertas", ofertaService.findOfertaByEmpresa(idEmpresa));
//		model.addAttribute("ofertas", ofertaService.findAll());
		model.addAttribute("titulo", "Mis ofertas publicadas:");
		return "views/oferta/listado";
	}

	@GetMapping(value = "/detalle/{idOfertaEmpleo}")
	public String mostrarDetalle(@PathVariable(value = "idOfertaEmpleo") Integer idOfertaEmpleo, Model model) {
		Optional<OfertaEmpleo> oferta = null;
		if (idOfertaEmpleo > 0) {
			oferta = ofertaService.findById(idOfertaEmpleo);
			model.addAttribute("titulo", oferta.get().getTituloOferta());
			model.addAttribute("desc", "Descripción");
			model.addAttribute("descOferta", oferta.get().getDescripcionOferta());
			model.addAttribute("empresa", empresaService.findEmpresa(oferta.get()));

		}
		return "views/oferta/detalle";
	}

	@GetMapping(value = "/formulario")
	public String crearOferta(Model model) {
		OfertaEmpleo oferta = new OfertaEmpleo();
		Optional<Empresa> emp = empresaService.findById(1);
		model.addAttribute("empresa", emp);
		model.addAttribute("titulo", "Formulario de ofertas");
		model.addAttribute("value", "Añadir");
		model.addAttribute("provincias", Provincias.values());
		model.addAttribute("sectores", sectorService.findAll());
		model.addAttribute("oferta", oferta);
		return "views/oferta/formulario";
	}

	@GetMapping(value = "/formulario/{idOfertaEmpleo}")
	public String modificarOferta(@PathVariable(value = "idOfertaEmpleo") Integer idOfertaEmpleo, Model model) {
		Optional<OfertaEmpleo> oferta = null;
		if (idOfertaEmpleo > 0) {
			oferta = ofertaService.findById(idOfertaEmpleo);
		} else {
			return "redirect:oferta/listado";
		}
		model.addAttribute("provincias", Provincias.values());
		model.addAttribute("sectores", sectorService.findAll());
		model.addAttribute("oferta", oferta);
		model.addAttribute("value", "Añadir");
		model.addAttribute("titulo", "Formulario de ofertas");
		return "views/oferta/formulario";
	}

	@PostMapping(value = "/guardar")
	public String guardarOferta(@Valid @ModelAttribute("oferta") OfertaEmpleo oferta, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de ofertas");
			model.addAttribute("value", "Añadir");
			model.addAttribute("provincias", Provincias.values());
			model.addAttribute("sectores", sectorService.findAll());
			return "views/oferta/formulario";
		} else if (oferta.getFechaFinOferta().before(oferta.getFechaInicioOferta())) {
			model.addAttribute("titulo", "Formulario de ofertas");
			model.addAttribute("provincias", Provincias.values());
			model.addAttribute("sectores", sectorService.findAll());
			model.addAttribute("value", "Añadir");
			model.addAttribute("msgFecha", "La fecha de finalización no puede ser anterior a la inicial.");
			return "views/oferta/formulario";
		}
		oferta.setEmpresa(empresaService.findById(1).get());
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
		model.addAttribute("pregunta","¿Estás seguro/a de eliminar esta oferta?");
		model.addAttribute("msg","Una vez eliminada,¡no se prodrá restablecer!");
		model.addAttribute("id",idOfertaEmpleo);
		return "views/oferta/confirmar";
	}
}
