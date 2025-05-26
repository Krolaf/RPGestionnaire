package com.rpg.RPGestionnaire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/front")
public class FrontController {
    @GetMapping
    public Object accueil() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
            boolean isMJ = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_MJ"));
            boolean isJoueur = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_JOUEUR"));
            if (isAdmin) {
                return new RedirectView("/admin");
            } else if (isMJ) {
                return new RedirectView("/front/gm/dashboard");
            } else if (isJoueur) {
                return new RedirectView("/front/player/dashboard");
            }
        }
        return "front/index";
    }
} 