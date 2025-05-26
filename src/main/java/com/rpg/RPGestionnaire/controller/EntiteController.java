package com.rpg.RPGestionnaire.controller;

import com.rpg.RPGestionnaire.entity.Entite;
import com.rpg.RPGestionnaire.service.EntiteService;
import com.rpg.RPGestionnaire.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Controller
public class EntiteController {
    @Autowired
    private EntiteService entiteService;
    @Autowired
    private UtilisateurService utilisateurService;

    // ADMIN : CRUD global
    @GetMapping("/admin/entites")
    public String listeAdmin(Model model, Authentication auth) {
        if (hasRole(auth, "ROLE_ADMIN")) {
            model.addAttribute("entites", entiteService.findAll());
            return "admin/entite/liste";
        }
        return "error/403";
    }

    @GetMapping("/admin/entites/ajouter")
    public String formulaireAjoutAdmin(Model model, Authentication auth) {
        if (hasRole(auth, "ROLE_ADMIN")) {
            Entite entite = new Entite();
            model.addAttribute("entite", entite);
            return "admin/entite/formulaire";
        }
        return "error/403";
    }

    @PostMapping("/admin/entites/ajouter")
    public String enregistrerAdmin(@ModelAttribute Entite entite, Authentication auth) {
        if (hasRole(auth, "ROLE_ADMIN")) {
            entiteService.save(entite);
            return "redirect:/admin/entites";
        }
        return "error/403";
    }

    @GetMapping("/admin/entites/editer/{id}")
    public String formulaireEditionAdmin(@PathVariable UUID id, Model model, Authentication auth) {
        if (hasRole(auth, "ROLE_ADMIN")) {
            Entite entite = entiteService.findById(id).orElseThrow();
            model.addAttribute("entite", entite);
            return "admin/entite/formulaire";
        }
        return "error/403";
    }

    @PostMapping("/admin/entites/editer/{id}")
    public String modifierAdmin(@PathVariable UUID id, @ModelAttribute Entite entite, Authentication auth) {
        if (hasRole(auth, "ROLE_ADMIN")) {
            entite.setId(id);
            entiteService.save(entite);
            return "redirect:/admin/entites";
        }
        return "error/403";
    }

    @GetMapping("/admin/entites/supprimer/{id}")
    public String supprimerAdmin(@PathVariable UUID id, Authentication auth) {
        if (hasRole(auth, "ROLE_ADMIN")) {
            entiteService.deleteById(id);
            return "redirect:/admin/entites";
        }
        return "error/403";
    }

    // MJ : Gestion des entités dans une partie
    @GetMapping("/gm/partie/{partieId}/entites")
    public String listeMjPartie(@PathVariable UUID partieId, Model model, Authentication auth) {
        if (hasRole(auth, "ROLE_MJ")) {
            // TODO : Vérifier que le MJ a le droit sur la partie
            model.addAttribute("entites", entiteService.findByPartieId(partieId));
            return "gm/entite/liste";
        }
        return "error/403";
    }

    @GetMapping("/gm/partie/{partieId}/entite/{id}")
    public String voirEntiteMj(@PathVariable UUID partieId, @PathVariable UUID id, Model model, Authentication auth) {
        if (hasRole(auth, "ROLE_MJ")) {
            // TODO : Vérifier que le MJ a le droit sur la partie
            Entite entite = entiteService.findById(id).orElseThrow();
            model.addAttribute("entite", entite);
            return "gm/entite/voir";
        }
        return "error/403";
    }

    private boolean hasRole(Authentication auth, String role) {
        return auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(role));
    }
} 