package com.teamfaceless.facelessjobs.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teamfaceless.facelessjobs.dtos.filtro.Filtro;
import com.teamfaceless.facelessjobs.model.Candidato;
import com.teamfaceless.facelessjobs.model.Credencial;
import com.teamfaceless.facelessjobs.model.Empresa;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;
import com.teamfaceless.facelessjobs.model.Provincia;
import com.teamfaceless.facelessjobs.model.SectorLaboral;
import com.teamfaceless.facelessjobs.paginator.PageRender;
import com.teamfaceless.facelessjobs.services.ICandidatoService;
import com.teamfaceless.facelessjobs.services.ICredencialService;
import com.teamfaceless.facelessjobs.services.IEmpresaService;
import com.teamfaceless.facelessjobs.services.IOfertaService;
import com.teamfaceless.facelessjobs.services.IProvinciaService;
import com.teamfaceless.facelessjobs.services.ISectorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("main")
@RequestMapping("/")
public class MainController {
@Autowired
private IProvinciaService  provinciaService;
    @Autowired
    private IOfertaService ofertaService;

    @Autowired
    private ICredencialService credencialService;

    @Autowired
    private ICandidatoService candidatoService;

    @Autowired
    private IEmpresaService empresaService;
@Autowired
private ISectorService sectorService;
    @Autowired
    private HttpSession httpSession;
    private final Integer N_ELEMENTOS=6;//items por pagina

//    @GetMapping("/")
//    public String goToIndex(Model model, @RequestParam(name = "page", defaultValue = "0") int page,@RequestParam(name="titulo",required=false)String titulo,
//    		@RequestParam(name="descrpcion",required=false)String descripcion,@RequestParam(name="provincia",required=false)String provincia,@RequestParam(name="sector",required=false)String sector,
//    		@RequestParam(name="salarioMinimo",required=false)Integer salarioMinimo,@RequestParam(name="salarioMaximo",required=false)Integer salarioMaximo) {
//        if(titulo!=null) { 
//        	List<OfertaEmpleo>ofertas=ofertaService.findByTituloAndDescripcion(titulo, descripcion, provincia, sector, salarioMinimo, salarioMaximo, salarioMinimo, salarioMaximo);
//        	model.addAttribute("ofertas", ofertas);
//        }
//    	
//    	Pageable pageRequest = PageRequest.of(page, 8);
//        Page<OfertaEmpleo> ofertas = ofertaService.findAllPageable(pageRequest);
//        PageRender<OfertaEmpleo> pageRender = new PageRender<>("/", ofertas);
//        model.addAttribute("pageTitle", "Inicio");
//        model.addAttribute("ofertas", ofertas);
//        model.addAttribute("page", pageRender);
//        return "/views/generic/index";
//    }
	@GetMapping(value ="/" )
	public String filtrar(  
			@RequestParam(required = false)String titulo,
			@RequestParam(required = false)String descripcion,
			@RequestParam(required = false)String provincia,
			@RequestParam(required = false)String sector,
			@RequestParam(required = false)Integer salarioMinimo,
			@RequestParam(required = false)Integer salarioMaximo,
			 @RequestParam(name = "page", defaultValue = "0")Integer nPagina,HttpSession session,Model model){//comienza en 0
		Filtro filtro=new Filtro(titulo,descripcion,provincia,sector,salarioMinimo,salarioMaximo,nPagina);
		if(session.getAttribute("filtro")==null) {
			session.setAttribute("filtro", filtro);
		}else {
		Filtro filtro2=(Filtro)session.getAttribute("filtro");
		filtro2.setTitulo(filtro.getTitulo()!=""?filtro.getTitulo():filtro2.getTitulo());
		filtro2.setDescripcion(filtro.getDescripcion()!=""?filtro.getDescripcion():filtro2.getDescripcion());
		filtro2.setProvincia(filtro.getProvincia()!=""?filtro.getProvincia():filtro2.getProvincia());
		filtro2.setSector(filtro.getSector()!=""?filtro.getSector():filtro2.getSector());
		filtro2.setSalarioMinimo(filtro.getSalarioMinimo()!=null?filtro.getSalarioMinimo():filtro2.getSalarioMinimo());
		filtro2.setSalarioMaximo(filtro.getSalarioMaximo()!=null?filtro.getSalarioMaximo():filtro2.getSalarioMaximo());
		filtro2.setNPagina(filtro.getNPagina()!=null?filtro.getNPagina():filtro2.getNPagina());
		
			session.setAttribute("filtro", filtro2);
		}
		Filtro filtro3=(Filtro)session.getAttribute("filtro");
		
		Page<OfertaEmpleo>ofertas=ofertaService.findByTituloAndDescripcion(//
				filtro3.getTitulo(),filtro3.getDescripcion(),filtro3.getProvincia(),filtro3.getSector(),filtro3.getSalarioMinimo(),filtro3.getSalarioMaximo(),filtro3.getNPagina(),N_ELEMENTOS);
		PageRender<OfertaEmpleo> pageRender = new PageRender<>("/", ofertas);
		if(ofertas.isEmpty()) {
			model.addAttribute("msgError","No se encontró ningun resultado asociado a su busqueda.");
		}
		List<Provincia> provincias=provinciaService.findAll();
		List<SectorLaboral>sectores=sectorService.findAll();
		
		model.addAttribute("ofertas",ofertas);
		model.addAttribute("page", pageRender);
		model.addAttribute("pageTitle","Inicio");
		model.addAttribute("provincias", provincias);
		model.addAttribute("sectores", sectores);
		  return "/views/generic/index";
	}
	@GetMapping("/limpiarFiltro")
	public String limpiarFiltro(HttpSession session) {
		session.removeAttribute("filtro");
		return "redirect:/";
	}

    @GetMapping("/offer/{idOffer}")
    public String goToOffers() {
        return "/views/generic/offerdetail";
    }

    @GetMapping("/about")
    public String goToAbout() {
        return "/views/generic/about";
    }

    @GetMapping("/contact")
    public String goToContact() {
        return "/views/generic/contact";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, null);
        return "/";
    }

    @GetMapping("/login")
    public String formLogin(Model model, @RequestParam(value = "error", required = false) String error) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/";
        }

        if (error != null) {
            model.addAttribute("msgError", "Credenciales incorrectas");
        }
        return "views/generic/login";
    }

    @GetMapping("/afterlogin")
    public String formLogin(Model model, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            List<GrantedAuthority> roles = (List<GrantedAuthority>) auth.getAuthorities();
            if (roles.get(0).getAuthority().equals("ROLE_CANDIDATO")) {
                String accountName = auth.getName();
                Optional<Credencial> credencial = credencialService.findByEmail(accountName);

                if (credencial.isPresent()) {

                   // Optional<Candidato> candidato = candidatoService.findById(credencial.get().getIdCredencial());
                   Optional<Candidato> candidato = candidatoService.findByEmail(accountName);// buscarPorId(credencial.get().getIdCredencial());
                    if (candidato.isPresent()) {
                        model.addAttribute("candidato", candidato.get());
                        httpSession.setAttribute("userSession", candidato.get());
                        httpSession.setAttribute("credencialSession", credencial.get());
                        return "redirect:/";
                    }
                }
            } else if (roles.get(0).getAuthority().equals("ROLE_EMPRESA")) {
                String accountName = auth.getName();
                Optional<Credencial> credencial = credencialService.findByEmail(accountName);
                if (credencial.isPresent()) {
                   // Optional<Empresa> empresa = empresaService.findById(credencial.get().getIdCredencial());
                    Optional<Empresa> empresa = empresaService.findByEmailEmpresa(accountName);// buscarPorId(credencial.get().getIdCredencial());
                    if (empresa.isPresent()) {
                        model.addAttribute("empresa", empresa.get());
                        httpSession.setAttribute("userSession", empresa.get());
                        httpSession.setAttribute("credencialSession", credencial.get());
                        return "redirect:/";
                    }
                }
            }
        }

        return "/login";

    }

    @GetMapping("/credencial/modify")
    public String goToCredencialModify(Model model) {
        model.addAttribute("sessionCredencial", httpSession.getAttribute("credencialSession"));

        return "views/app/credencialmodify";
    }

}
