package com.rpg.RPGestionnaire.controller;

import com.rpg.RPGestionnaire.entity.Partie;
import com.rpg.RPGestionnaire.service.PartieService;
import com.rpg.RPGestionnaire.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Controller
public class PartieController {
    @Autowired
    private PartieService partieService;
    @Autowired
    private UtilisateurService utilisateurService;

    // Liste des parties (admin ou GM)
    @GetMapping({"/admin/parties", "/parties/gm"})
    public String liste(Model model, Authentication auth) {
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            model.addAttribute("parties", partieService.findAll());
            return "admin/partie/liste";
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_MJ"))) {
            String username = auth.getName();
            model.addAttribute("parties", partieService.findByGmUsername(username));
            return "front/gm/dashboard"; // ou un template dédié GM si besoin
        }
        return "error/403";
    }

    // Formulaire ajout
    @GetMapping({"/admin/parties/ajouter", "/parties/gm/ajouter"})
    public String formulaireAjout(Model model, Authentication auth) {
        model.addAttribute("partie", new Partie());
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "admin/partie/formulaire";
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_MJ"))) {
            return "front/gm/partie-formulaire";
        }
        return "error/403";
    }

    // Ajout
    @PostMapping({"/admin/parties/ajouter", "/parties/gm/ajouter"})
    public String enregistrer(@ModelAttribute Partie partie, Authentication auth) {
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            partieService.save(partie);
            return "redirect:/admin/parties";
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_MJ"))) {
            String username = auth.getName();
            partie.setMj(utilisateurService.findByPseudo(username).orElse(null));
            partieService.save(partie);
            return "redirect:/front/gm/dashboard?tab=games";
        }
        return "error/403";
    }

    // Formulaire édition
    @GetMapping({"/admin/parties/editer/{id}", "/parties/gm/editer/{id}"})
    public String formulaireEdition(@PathVariable UUID id, Model model, Authentication auth) {
        Partie partie = partieService.findById(id).orElseThrow();
        model.addAttribute("partie", partie);
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "admin/partie/formulaire";
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_MJ"))) {
            return "front/gm/partie-formulaire";
        }
        return "error/403";
    }

    // Edition
    @PostMapping({"/admin/parties/editer/{id}", "/parties/gm/editer/{id}"})
    public String modifier(@PathVariable UUID id, @ModelAttribute Partie partie, Authentication auth) {
        partie.setId(id);
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            partieService.save(partie);
            return "redirect:/admin/parties";
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_MJ"))) {
            String username = auth.getName();
            partie.setMj(utilisateurService.findByPseudo(username).orElse(null));
            partieService.save(partie);
            return "redirect:/front/gm/dashboard?tab=games";
        }
        return "error/403";
    }

    // Suppression
    @GetMapping({"/admin/parties/supprimer/{id}", "/parties/gm/supprimer/{id}"})
    public String supprimer(@PathVariable UUID id, Authentication auth) {
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            partieService.deleteById(id);
            return "redirect:/admin/parties";
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_MJ"))) {
            partieService.deleteById(id);
            return "redirect:/front/gm/dashboard?tab=games";
        }
        return "error/403";
    }
} 