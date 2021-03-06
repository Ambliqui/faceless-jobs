package com.teamfaceless.facelessjobs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamfaceless.facelessjobs.enums.Categoria;
import com.teamfaceless.facelessjobs.model.Habilidad;
import com.teamfaceless.facelessjobs.services.IHabilidadService;



@Controller
@RequestMapping("/app/admin/habilidad")
public class ControllerHabilidad {
	
	@Autowired
	private IHabilidadService iHabilidadService;
	
	
	@GetMapping("/listado")
	public String goListado(Model model) {
		model.addAttribute("titulo", "Listado de Habilidades");
		model.addAttribute("habilidadesDuras", iHabilidadService.findHabilidadesDuras());
		model.addAttribute("habilidadesBlandas", iHabilidadService.findHabilidadesBlandas());
		return "/views/app/admin/habilidad/listado";
	}
	/*
	// CONTROLADOR PARA COMPROBAR QUE FUNCIONAN LOS FILTROS POR CATEGORÍA
	// CAMBIAR "categoria.BLANDA" por "categoria.DURA" Y SE VERÁ
	
	@GetMapping("/listadoFiltrado")
	public String goListadoFiltrado(Model model) {
		model.addAttribute("titulo", "Listado de Habilidades");
		model.addAttribute("habilidades", iHabilidadService.findHabilidadByCategoria(Categoria.DURA));
		return "views/habilidad/listado";
	}
	*/
	
	@PostMapping("/listado")
	public String goListadoPost(Model model) {
		model.addAttribute("titulo", "Listado de Habilidades");
		model.addAttribute("habilidadesDuras", iHabilidadService.findHabilidadesDuras());
		model.addAttribute("habilidadesBlandas", iHabilidadService.findHabilidadesBlandas());
		return "/views/app/admin/habilidad/listado";
	}
	
	@GetMapping("/formulario")
	public String goFormulario(Model model) {
		Habilidad habilidad = new Habilidad();
		model.addAttribute("habilidad", habilidad);
		model.addAttribute("nombrePlaceholder", "Nombre...");
		model.addAttribute("descripcionPlaceholder", "Descripción...");
		model.addAttribute("submitValue", "Añadir");
		model.addAttribute("categorias", Categoria.values());
		model.addAttribute("titulo", "Crear Habilidad");
		return "/views/app/admin/habilidad/formulario";
	}
	
	@GetMapping("/formulario/{idHabilidad}")
	public String goFormularioConId(@PathVariable Integer idHabilidad, Model model) {
		model.addAttribute("categorias", Categoria.values());
		if(iHabilidadService.findById(idHabilidad).isPresent()) {
			Habilidad habilidad = iHabilidadService.findById(idHabilidad).get();
			model.addAttribute("habilidad", iHabilidadService.findById(idHabilidad));
			model.addAttribute("nombrePlaceholder", habilidad.getNombreHabilidad());
			model.addAttribute("descripcionPlaceholder", habilidad.getDescripcionHabilidad());
			model.addAttribute("categoriaPlaceholder", habilidad.getCategoriaHabilidad());
			model.addAttribute("titulo", "Editar Habilidad");
			model.addAttribute("submitValue", "Editar");
		}
		else {
			return "redirect:/app/admin/habilidad/formulario";
		}
		return "/views/app/admin/habilidad/formulario";
	}
	
	@PostMapping("/guardar")
	public String altaCategoria(@Valid Habilidad habilidad, BindingResult result, Model model) {
		if(result.hasErrors()) {
			Integer idHabilidad = habilidad.getIdHabilidad();
			if(idHabilidad!=null) {
				model.addAttribute("categorias", Categoria.values());
				model.addAttribute("habilidad", habilidad);
				model.addAttribute("nombrePlaceholder", habilidad.getNombreHabilidad());
				model.addAttribute("descripcionPlaceholder", habilidad.getDescripcionHabilidad());
				model.addAttribute("categoriaPlaceholder", habilidad.getCategoriaHabilidad());
				model.addAttribute("titulo", "Editar Habilidad");
				model.addAttribute("submitValue", "Editar");
				return "/views/app/admin/habilidad/formulario";				
			}
			else {
				model.addAttribute("habilidad", habilidad);
				model.addAttribute("nombrePlaceholder", "Nombre...");
				model.addAttribute("descripcionPlaceholder", "Descripción...");
				model.addAttribute("submitValue", "Añadir");
				model.addAttribute("categorias", Categoria.values());
				model.addAttribute("titulo", "Crear Habilidad");
				return "/views/app/admin/habilidad/formulario";
			}
		}
		
			iHabilidadService.modify(habilidad);
		
		return "redirect:/app/admin/habilidad/listado";
	}
	
	@GetMapping("/eliminar/{idHabilidad}")
	public String goBorrar(@PathVariable Integer idHabilidad, Model model) {
		iHabilidadService.delete(idHabilidad);
		model.addAttribute("titulo", "Listado de Habilidades");
		model.addAttribute("habilidades", iHabilidadService.findAll());
		return "/views/app/admin/habilidad/formulario";
	}
}
