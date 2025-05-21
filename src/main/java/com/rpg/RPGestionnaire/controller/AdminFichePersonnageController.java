package com.rpg.RPGestionnaire.controller;

import com.rpg.RPGestionnaire.entity.FichePersonnage;
import com.rpg.RPGestionnaire.service.FichePersonnageService;
import com.rpg.RPGestionnaire.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("/admin/fiches")
public class AdminFichePersonnageController {
    @Autowired
    private FichePersonnageService fichePersonnageService;

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public String liste(Model model) {
        model.addAttribute("fiches", fichePersonnageService.findAll());
        return "admin/fiche/liste";
    }

    @GetMapping("/ajouter")
    public String formulaireAjout(Model model) {
        FichePersonnage fiche = new FichePersonnage();
        fiche.setStats(new ArrayList<>());
        model.addAttribute("fichePersonnage", fiche);
        model.addAttribute("utilisateurs", utilisateurService.findAll());
        return "admin/fiche/formulaire";
    }

    @PostMapping("/ajouter")
    public String enregistrer(@ModelAttribute FichePersonnage fichePersonnage, @RequestParam("utilisateur") UUID utilisateurId) {
        fichePersonnage.setUtilisateur(utilisateurService.findById(utilisateurId).orElse(null));
        if (fichePersonnage.getStats() != null) {
            fichePersonnage.setStats(fichePersonnage.getStats().stream().filter(s -> s.getNom() != null && !s.getNom().isBlank()).toList());
            fichePersonnage.getStats().forEach(s -> s.setFichePersonnage(fichePersonnage));
        }
        fichePersonnageService.save(fichePersonnage);
        return "redirect:/admin/fiches";
    }

    @GetMapping("/editer/{id}")
    public String formulaireEdition(@PathVariable UUID id, Model model) {
        FichePersonnage fiche = fichePersonnageService.findById(id).orElseThrow();
        model.addAttribute("fichePersonnage", fiche);
        model.addAttribute("utilisateurs", utilisateurService.findAll());
        return "admin/fiche/formulaire";
    }

    @PostMapping("/editer/{id}")
    public String modifier(@PathVariable UUID id, @ModelAttribute FichePersonnage fichePersonnage, @RequestParam("utilisateur") UUID utilisateurId) {
        fichePersonnage.setId(id);
        fichePersonnage.setUtilisateur(utilisateurService.findById(utilisateurId).orElse(null));
        if (fichePersonnage.getStats() != null) {
            fichePersonnage.setStats(fichePersonnage.getStats().stream().filter(s -> s.getNom() != null && !s.getNom().isBlank()).toList());
            fichePersonnage.getStats().forEach(s -> s.setFichePersonnage(fichePersonnage));
        }
        fichePersonnageService.save(fichePersonnage);
        return "redirect:/admin/fiches";
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable UUID id) {
        fichePersonnageService.deleteById(id);
        return "redirect:/admin/fiches";
    }
} 