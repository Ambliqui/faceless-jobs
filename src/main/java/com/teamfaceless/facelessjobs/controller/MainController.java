package com.teamfaceless.facelessjobs.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamfaceless.facelessjobs.services.IOfertaService;

@Controller("main")
@RequestMapping("/")
public class MainController {
	@Autowired
	private IOfertaService ofertaService;

    @GetMapping("/")
    public String goToIndex(Model model,Pageable page) {
        model.addAttribute("pageTitle", "Inicio");
        model.addAttribute("ofertas",ofertaService.findAllPageable(page));
        return "/rendered/generic/index";
    }

    @GetMapping("/offer/{idOffer}")
    public String goToOffers() {
        return "/rendered/generic/offerdetail";
    }

    @GetMapping("/about")
    public String goToAbout() {
        return "/rendered/generic/about";
    }

    @GetMapping("/register")
    public String goToRegister(Model model) {
        return "/rendered/forms/register";
    }

    @GetMapping("/login")
    public String goToLogin() {
        return "/rendered/forms/login";
    }

    @GetMapping("/contact")
    public String goToContact() {
        return "/rendered/generic/contact";
    }


    
}
