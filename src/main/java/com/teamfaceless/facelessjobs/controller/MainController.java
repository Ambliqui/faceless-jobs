package com.teamfaceless.facelessjobs.controller;

import javax.servlet.http.HttpServletRequest;

import com.teamfaceless.facelessjobs.model.OfertaEmpleo;
import com.teamfaceless.facelessjobs.paginator.PageRender;
import com.teamfaceless.facelessjobs.services.IOfertaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        return "/rendered/generic/offerdetail";
    }

    @GetMapping("/about")
    public String goToAbout() {
        return "/rendered/generic/about";
    }

    @GetMapping("/contact")
    public String goToContact() {
        return "/rendered/generic/contact";
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null);
        return "redirect:/";
    }
    
}
