package com.rpg.RPGestionnaire.controller;

import com.rpg.RPGestionnaire.service.FichePersonnageService;
import com.rpg.RPGestionnaire.service.PartieService;
import com.rpg.RPGestionnaire.entity.FichePersonnage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("/front/player")
public class PlayerController {
    @Autowired
    private FichePersonnageService fichePersonnageService;
    @Autowired
    private PartieService partieService;

    @GetMapping("/dashboard")
    public String playerDashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        // Placeholder : à remplacer par la vraie méthode quand les invitations seront gérées
        model.addAttribute("games", Collections.emptyList());
        model.addAttribute("characters", fichePersonnageService.findByUtilisateurPseudo(username));
        model.addAttribute("session", java.util.Map.of("user", java.util.Map.of("username", username)));
        model.addAttribute("newPersonnage", new FichePersonnage());
        return "front/player/dashboard";
    }
} 