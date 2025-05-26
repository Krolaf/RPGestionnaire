package com.rpg.RPGestionnaire.controller;

import com.rpg.RPGestionnaire.entity.Entite;
import com.rpg.RPGestionnaire.service.EntiteService;
import com.rpg.RPGestionnaire.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    // Liste des entités (admin ou GM)
    @GetMapping({"/admin/entites", "/entites/gm"})
    public String liste(Model model, Authentication auth) {
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            model.addAttribute("entites", entiteService.findAll());
            return "admin/entite/liste";
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_MJ"))) {
            String username = auth.getName();
            model.addAttribute("entites", entiteService.findByGmUsername(username));
            return "front/gm/dashboard"; // ou un template dédié GM si besoin
        }
        return "error/403";
    }

    // Formulaire ajout
    @GetMapping({"/admin/entites/ajouter", "/entites/gm/ajouter"})
    public String formulaireAjout(Model model, Authentication auth) {
        model.addAttribute("entite", new Entite());
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "admin/entite/formulaire";
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_MJ"))) {
            return "front/gm/entite-formulaire";
        }
        return "error/403";
    }

    // Ajout
    @PostMapping({"/admin/entites/ajouter", "/entites/gm/ajouter"})
    public String enregistrer(@ModelAttribute Entite entite, Authentication auth) {
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            entiteService.save(entite);
            return "redirect:/admin/entites";
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_MJ"))) {
            String username = auth.getName();
            entite.setCreateur(utilisateurService.findByPseudo(username).orElse(null));
            entiteService.save(entite);
            return "redirect:/front/gm/dashboard?tab=entities";
        }
        return "error/403";
    }

    // Formulaire édition
    @GetMapping({"/admin/entites/editer/{id}", "/entites/gm/editer/{id}"})
    public String formulaireEdition(@PathVariable UUID id, Model model, Authentication auth) {
        Entite entite = entiteService.findById(id).orElseThrow();
        model.addAttribute("entite", entite);
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "admin/entite/formulaire";
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_MJ"))) {
            return "front/gm/entite-formulaire";
        }
        return "error/403";
    }

    // Edition
    @PostMapping({"/admin/entites/editer/{id}", "/entites/gm/editer/{id}"})
    public String modifier(@PathVariable UUID id, @ModelAttribute Entite entite, Authentication auth) {
        entite.setId(id);
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            entiteService.save(entite);
            return "redirect:/admin/entites";
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_MJ"))) {
            String username = auth.getName();
            entite.setCreateur(utilisateurService.findByPseudo(username).orElse(null));
            entiteService.save(entite);
            return "redirect:/front/gm/dashboard?tab=entities";
        }
        return "error/403";
    }

    // Suppression
    @GetMapping({"/admin/entites/supprimer/{id}", "/entites/gm/supprimer/{id}"})
    public String supprimer(@PathVariable UUID id, Authentication auth) {
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            entiteService.deleteById(id);
            return "redirect:/admin/entites";
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_MJ"))) {
            entiteService.deleteById(id);
            return "redirect:/front/gm/dashboard?tab=entities";
        }
        return "error/403";
    }
} 