package com.rpg.RPGestionnaire.controller;

import com.rpg.RPGestionnaire.entity.Utilisateur;
import com.rpg.RPGestionnaire.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Controller
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    // Liste des utilisateurs (admin)
    @GetMapping("/admin/utilisateurs")
    public String listeAdmin(Model model, Authentication auth) {
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            model.addAttribute("utilisateurs", utilisateurService.findAll());
            return "admin/utilisateur/liste";
        }
        return "error/403";
    }

    // Formulaire ajout
    @GetMapping("/admin/utilisateurs/ajouter")
    public String formulaireAjoutAdmin(Model model, Authentication auth) {
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            model.addAttribute("utilisateur", new Utilisateur());
            return "admin/utilisateur/formulaire";
        }
        return "error/403";
    }

    // Ajout
    @PostMapping("/admin/utilisateurs/ajouter")
    public String enregistrerAdmin(@ModelAttribute Utilisateur utilisateur, Authentication auth) {
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            utilisateurService.save(utilisateur);
            return "redirect:/admin/utilisateurs";
        }
        return "error/403";
    }

    // Formulaire édition
    @GetMapping("/admin/utilisateurs/editer/{id}")
    public String formulaireEditionAdmin(@PathVariable UUID id, Model model, Authentication auth) {
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            Utilisateur utilisateur = utilisateurService.findById(id).orElseThrow();
            model.addAttribute("utilisateur", utilisateur);
            return "admin/utilisateur/formulaire";
        }
        return "error/403";
    }

    // Edition
    @PostMapping("/admin/utilisateurs/editer/{id}")
    public String modifierAdmin(@PathVariable UUID id, @ModelAttribute Utilisateur utilisateur, Authentication auth) {
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            utilisateur.setId(id);
            utilisateurService.save(utilisateur);
            return "redirect:/admin/utilisateurs";
        }
        return "error/403";
    }

    // Suppression
    @GetMapping("/admin/utilisateurs/supprimer/{id}")
    public String supprimerAdmin(@PathVariable UUID id, Authentication auth) {
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            utilisateurService.deleteById(id);
            return "redirect:/admin/utilisateurs";
        }
        return "error/403";
    }
} 