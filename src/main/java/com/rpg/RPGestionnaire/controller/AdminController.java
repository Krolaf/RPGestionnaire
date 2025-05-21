package com.rpg.RPGestionnaire.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @GetMapping
    public String index() {
        return "admin/index";
    }

    @GetMapping({"/", "/index"})
    public RedirectView redirectAdminToBackoffice() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_Admin"))) {
            return new RedirectView("/admin");
        }
        return new RedirectView("/home"); // page d'accueil classique pour les non-admins (à adapter)
    }
} 