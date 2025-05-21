package com.rpg.RPGestionnaire.controller;

import com.rpg.RPGestionnaire.service.PartieService;
import com.rpg.RPGestionnaire.entity.Partie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.UUID;

@Controller
@RequestMapping("/admin/parties")
public class AdminPartieController {

    @Autowired
    private PartieService partieService;

    @GetMapping
    public String liste(Model model) {
        model.addAttribute("parties", partieService.findAll());
        return "admin/partie/liste";
    }

    @GetMapping("/ajouter")
    public String afficherFormulaireAjout(Model model) {
        model.addAttribute("partie", new Partie());
        return "admin/partie/formulaire";
    }

    @PostMapping("/ajouter")
    public String ajouterPartie(@ModelAttribute Partie partie) {
        partieService.save(partie);
        return "redirect:/admin/parties";
    }

    @GetMapping("/editer/{id}")
    public String afficherFormulaireEdition(@PathVariable("id") UUID id, Model model) {
        Partie partie = partieService.findById(id).orElseThrow();
        model.addAttribute("partie", partie);
        return "admin/partie/formulaire";
    }

    @PostMapping("/editer/{id}")
    public String editerPartie(@PathVariable("id") UUID id, @ModelAttribute Partie partie) {
        partie.setId(id);
        partieService.save(partie);
        return "redirect:/admin/parties";
    }

    @GetMapping("/supprimer/{id}")
    public String supprimerPartie(@PathVariable("id") UUID id) {
        partieService.deleteById(id);
        return "redirect:/admin/parties";
    }

    // Ajoute ici les méthodes pour ajouter, éditer, supprimer si besoin
} 