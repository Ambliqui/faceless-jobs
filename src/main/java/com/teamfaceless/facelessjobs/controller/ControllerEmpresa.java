package com.teamfaceless.facelessjobs.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.teamfaceless.facelessjobs.dao.dtos.empresa.EmpresaListadoDto;
import com.teamfaceless.facelessjobs.dao.dtos.empresa.EmpresaRegistroDto;
import com.teamfaceless.facelessjobs.dao.dtos.empresa.mapper.IEmpresaMapper;
import com.teamfaceless.facelessjobs.model.Credencial;
import com.teamfaceless.facelessjobs.model.Empresa;
import com.teamfaceless.facelessjobs.services.IEmpresaService;
import com.teamfaceless.facelessjobs.services.IProvinciaService;
import com.teamfaceless.facelessjobs.services.IRolService;
import com.teamfaceless.facelessjobs.services.ISectorService;
import com.teamfaceless.facelessjobs.validations.IValidations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/empresa")
public class ControllerEmpresa {

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
	
	@GetMapping("/pruebas/{idEmpresa}")
	public String goPruebas(@PathVariable Integer idEmpresa, Model model) {
		model.addAttribute("provincias", iProvinciaService.findAll());
		model.addAttribute("sectores", iSectorService.findAll());
		model.addAttribute("empresa", iEmpresaService.findById(idEmpresa));
		return "views/empresa/pruebas";
	}
	
	@PostMapping("/pruebas")
	public String postPruebas(Empresa empresa, Model model, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("empresa", empresa);
			model.addAttribute("provincias", iProvinciaService.findAll());
			model.addAttribute("sectores", iSectorService.findAll());
			return "views/empresa/pruebas";
		}
		iEmpresaService.modify(empresa);
		return "redirect:/empresa/listado";
	}
	
	@GetMapping("/perfil")
	public String goPerfil(Model model) {
		model.addAttribute("titulo", "Perfil de Empresa");
		return "views/empresa/perfil";
	}
	
	@GetMapping("/listado")
	public String goListado(Model model) {
		model.addAttribute("listEmpresas", iEmpresaService.findAll());
		return "/views/empresa/listado";
	}
	
	@GetMapping("/registro")
	public String formRegistro(Model model, EmpresaRegistroDto empresaRegistroDto) {
		
		Map<String, String> mapaErrores = new HashMap<>();
		model.addAttribute("empresaRegistroDto", empresaRegistroDto);
		model.addAttribute("mapaErrores", mapaErrores);
		model.addAttribute("provincias", iProvinciaService.findAll());
		model.addAttribute("sectores", iSectorService.findAll());
		return "views/empresa/registro";
	}
	
	@PostMapping("/registro")
	public String registrarEmpresa(Model model, @Valid EmpresaRegistroDto empresaRegistroDto, BindingResult result) {
		//Crear validador personalizado para esta vista y devolver un mapa de errores a la vista
		Map<String, String> mapaErrores = new HashMap<>();
		
		iValidations.emailExistente(empresaRegistroDto.getEmailEmpresa())
			.ifPresent((error) -> mapaErrores.put("ErrorEmailExist", error.getMessage()));

		iValidations.camposCoincidentes(empresaRegistroDto.getConfirmEmailEmpresa(), empresaRegistroDto.getEmailEmpresa(), "Email", "Repite Email")
			.ifPresent((error) -> mapaErrores.put("errorEmailNoDuplicate", error.getMessage()));
		
		iValidations.camposCoincidentes(empresaRegistroDto.getPassEmpresa(), empresaRegistroDto.getConfirmPassEmpresa(), "Password", "Repite Password")
			.ifPresent((error) -> mapaErrores.put("errorPassNoDuplicate", error.getMessage()));
		
		iValidations.cifExistente(empresaRegistroDto.getCIFempresa())
			.ifPresent((error) -> mapaErrores.put("errorCIFExist", error.getMessage()));
		
		if (result.hasErrors()) {
			model.addAttribute("empresaRegistroDto", empresaRegistroDto);
			model.addAttribute("mapaErrores", mapaErrores);
			model.addAttribute("provincias", iProvinciaService.findAll());
			model.addAttribute("sectores", iSectorService.findAll());
			return "/views/empresa/registro";
		}
		
		iEmpresaService.create(iEmpresaMapper.empresaEmpresaDtoToEmpresa(empresaRegistroDto));
		return "redirect:/empresa/listado";
	}
	
	@GetMapping("/modificar/{idEmpresa}")
	public String goModificar(@PathVariable Integer idEmpresa, Model model) {
		model.addAttribute("empresa", iEmpresaService.findById(idEmpresa));
		model.addAttribute("empresaregistrodto", new EmpresaRegistroDto());
		model.addAttribute("provincias", iProvinciaService.findAll());
		model.addAttribute("sectores", iSectorService.findAll());
		return "/views/empresa/detalle";
	}
	
	@PostMapping("/modificar")
	public String modificarEmpresa(Model model, @Valid Empresa empresa, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("empresa", empresa);
			model.addAttribute("provincias", iProvinciaService.findAll());
			model.addAttribute("sectores", iSectorService.findAll());
			return "/views/empresa/detalle";
		}
		iEmpresaService.modify(empresa);
		return "redirect:/empresa/listado";
	}

	@GetMapping("/borrar/{idEmpresa}")
	public String formBaja(@PathVariable Integer idEmpresa) {
		iEmpresaService.delete(idEmpresa);
		return "redirect:/empresa/listado";
	}
	
	@PostMapping("/borrar/{idEmpresa}")
	public String bajaEmpresa(@PathVariable Integer idEmpresa) {
		iEmpresaService.delete(idEmpresa);
		return "redirect:/empresa/listado";
	}
	
	@GetMapping("/login")
	public String formLogin(Model model,@RequestParam(value = "error",required = false)String error) {
		model.addAttribute("credencial", new Credencial());
		if(error!=null) {
			model.addAttribute("msgError", "Credenciales incorrectas");
		}
		return"views/empresa/login";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("credencial")Credencial credencial,
			Model model, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return"views/empresa/login";
		}
		//loguear
		return"views/app/empresa/home";
	}
	
	@GetMapping("/pruebasValidation")
	private String goPruebasValidation(Model model) {
		model.addAttribute("empresa", new EmpresaListadoDto());
		return "/views/empresa/pruebasValidation";
	}
	
	@PostMapping("/pruebasValidation")
	private String goPruebasValidationReturn(Model model,@Valid EmpresaListadoDto empresaListadoDto, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("empresa", empresaListadoDto);
			return "/views/empresa/pruebasValidation";
		}
		return "redirect:/empresa/listado";
	}
	
}
