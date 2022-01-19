package com.teamfaceless.facelessjobs.controller;

import java.util.Date;

import com.teamfaceless.facelessjobs.exceptions.EmailExisteException;
import com.teamfaceless.facelessjobs.model.Candidato;
import com.teamfaceless.facelessjobs.model.Credencial;
import com.teamfaceless.facelessjobs.model.Empresa;
import com.teamfaceless.facelessjobs.model.Provincia;
import com.teamfaceless.facelessjobs.model.Rol;
import com.teamfaceless.facelessjobs.model.SectorLaboral;
import com.teamfaceless.facelessjobs.services.ICandidatoService;
import com.teamfaceless.facelessjobs.services.IEmpresaService;
import com.teamfaceless.facelessjobs.services.IOfertaService;
import com.teamfaceless.facelessjobs.services.IProvinciaService;
import com.teamfaceless.facelessjobs.services.IRolService;
import com.teamfaceless.facelessjobs.services.ISectorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//controller ejemplo
@Controller
public class ControllerPrueba {

	@Autowired
	private ICandidatoService candidatoService;
	
	@Autowired
	private IOfertaService iOfertaService;
	
	@Autowired
	private IEmpresaService empresaService;
	
	@Autowired
	private IRolService rolservice;
	
	@Autowired
	private IProvinciaService provinciaService;
	
@Autowired
private ISectorService sectorService;
	
	
//	@GetMapping({"/","/index","/home"})
//	public String index(Model model) {
//		model.addAttribute("ofertas",iOfertaService.findAll());
//		model.addAttribute("titulo", "Listado de ofertas");
//		return "views/oferta/listado";
////		return "/views/index";
//	}
	
	@GetMapping("/prueba")
	public String prueba(Model model) {
		
		
		
		if(candidatoService.findAll().isEmpty()) {
			
			
			
			try {
				rolservice.create(Rol.builder().nombre("ROLE_CANDIDATO").build());
				rolservice.create(Rol.builder().nombre("ROLE_EMPRESA").build());
				provinciaService.create(Provincia.builder().nombreProvincia("Córdoba").build());
				sectorService.create(SectorLaboral.builder().nombreSectorLaboral("Metal").build());
				candidatoService.create(Candidato.builder()
						.credencial(Credencial.builder()
								.email("pepe@gmail.com")
								.pass("candidato")
								.role(rolservice.findByNombre("ROLE_CANDIDATO"))
								.enable(true)
								.build())
						.apellidosCandidato("Pérez García")
						.nombreCandidato("Pepe")
						.fechaNacimientoCandidato(new Date(2020, 1, 20))
						.telefonoCandidato("666555444")
						.build()
						);
				candidatoService.create(Candidato.builder()
						.credencial(Credencial.builder()
								.email("maria@gmail.com")
								.pass("candidato")
								.role(rolservice.findByNombre("ROLE_CANDIDATO"))
								.enable(true)
								.build())
						.apellidosCandidato("Muñoz Sanchez")
						.nombreCandidato("Maria")
						.fechaNacimientoCandidato(new Date(2021, 2, 10))
						.telefonoCandidato("654321987")
						.build()
						);
			} catch (EmailExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			empresaService.create(Empresa.builder()
					.credencial(Credencial.builder()
							.email("empresa@gmail.com")
							.pass("empresa")
							.role(rolservice.findByNombre("ROLE_EMPRESA"))
							.enable(true)
							.build())
					
					.cIFempresa("123456")// peta en la validacion del CIF
					.direccionEmpresa("C/ de la empresa")
					.empleadosEmpresa(10)
					.localidadEmpresa("Locallidad empresa")
					.nombreEmpresa("Nombre empresa")
					.nombreJuridicoEmpresa("nombre kuridico")
					.provinciaEmpresa(provinciaService.findByNombre("Córdoba").get())
					.telefonoEmpresa("698745123")
					.sectorEmpresa(sectorService.findByNombreSector("Metal").get())
					.build()
					);
			
		}
		
		model.addAttribute("candidatos", candidatoService.findAll());
		
		return "/views/candidato/prueba";
	}
}
