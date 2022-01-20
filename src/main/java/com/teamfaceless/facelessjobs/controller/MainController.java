package com.teamfaceless.facelessjobs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teamfaceless.facelessjobs.model.OfertaEmpleo;
import com.teamfaceless.facelessjobs.paginator.PageRender;
import com.teamfaceless.facelessjobs.services.IOfertaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("main")
@RequestMapping("/")
public class MainController {
    @Autowired
    private IOfertaService ofertaService;

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
	public String formLogin(Model model,@RequestParam(value = "error",required = false)String error) {
		if(error!=null) {
			model.addAttribute("msgError", "Credenciales incorrectas");
		}
		return"views/generic/login";
	}

    // @PostMapping("login")
    // public void formLogin() {
    //     Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        

        
    // }
}
