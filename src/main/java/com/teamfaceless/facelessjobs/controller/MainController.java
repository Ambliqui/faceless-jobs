package com.teamfaceless.facelessjobs.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamfaceless.facelessjobs.model.Candidato;
import com.teamfaceless.facelessjobs.model.Credencial;
import com.teamfaceless.facelessjobs.model.Empresa;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;
import com.teamfaceless.facelessjobs.paginator.PageRender;
import com.teamfaceless.facelessjobs.services.ICandidatoService;
import com.teamfaceless.facelessjobs.services.ICredencialService;
import com.teamfaceless.facelessjobs.services.IEmpresaService;
import com.teamfaceless.facelessjobs.services.IOfertaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private IOfertaService ofertaService;

    @Autowired
    private ICredencialService credencialService;

    @Autowired
    private ICandidatoService candidatoService;

    @Autowired
    private IEmpresaService empresaService;

    @GetMapping("/")
    public String goToIndex(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageRequest = PageRequest.of(page, 6);
        Page<OfertaEmpleo> ofertas = ofertaService.findAllPageable(pageRequest);
        PageRender<OfertaEmpleo> pageRender = new PageRender<>("/", ofertas);
        model.addAttribute("pageTitle", "Inicio");
        model.addAttribute("ofertas", ofertas);
        model.addAttribute("page", pageRender);
        return "/views/generic/index";
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
                    Optional<Candidato> candidato = candidatoService.findById(credencial.get().getIdCredencial());
                    if (candidato.isPresent()) {
                        model.addAttribute("candidato", candidato.get());
                        return "redirect:/";
                    }
                }
            } else if (roles.get(0).getAuthority().equals("ROLE_EMPRESA")) {
                String accountName = auth.getName();
                Optional<Credencial> credencial = credencialService.findByEmail(accountName);
                if (credencial.isPresent()) {
                    Optional<Empresa> empresa = empresaService.findById(credencial.get().getIdCredencial());
                    if (empresa.isPresent()) {
                        model.addAttribute("empresa", empresa.get());
                        request.setAttribute("userSession", empresa);
                        return "redirect:/";
                    }
                }
            }
        }
        
        return "/login";

    }

}
