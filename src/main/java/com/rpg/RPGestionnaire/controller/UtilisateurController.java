package com.rpg.RPGestionnaire.controller;

import com.rpg.RPGestionnaire.entity.Utilisateur;
import com.rpg.RPGestionnaire.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/utilisateurs")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public String listeUtilisateurs(Model model) {
        List<Utilisateur> utilisateurs = utilisateurService.findAll();
        model.addAttribute("utilisateurs", utilisateurs);
        return "utilisateur/liste";
    }

    @GetMapping("/ajouter")
    public String afficherFormulaireAjout(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "utilisateur/formulaire";
    }

    @PostMapping("/ajouter")
    public String enregistrerUtilisateur(@ModelAttribute Utilisateur utilisateur) {
        utilisateurService.save(utilisateur);
        return "redirect:/utilisateurs";
    }
} 