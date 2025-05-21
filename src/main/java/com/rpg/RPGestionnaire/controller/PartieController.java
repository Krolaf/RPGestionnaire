package com.rpg.RPGestionnaire.controller;

import com.rpg.RPGestionnaire.entity.Partie;
import com.rpg.RPGestionnaire.service.PartieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Controller
@RequestMapping("/parties")
public class PartieController {
    @Autowired
    private PartieService partieService;

    @GetMapping
    public String liste(Model model) {
        model.addAttribute("parties", partieService.findAll());
        return "partie/liste";
    }

    @GetMapping("/ajouter")
    public String formulaireAjout(Model model) {
        model.addAttribute("partie", new Partie());
        return "partie/formulaire";
    }

    @PostMapping("/ajouter")
    public String enregistrer(@ModelAttribute Partie partie) {
        partieService.save(partie);
        return "redirect:/parties";
    }

    @GetMapping("/editer/{id}")
    public String formulaireEdition(@PathVariable UUID id, Model model) {
        Partie partie = partieService.findById(id).orElseThrow();
        model.addAttribute("partie", partie);
        return "partie/formulaire";
    }

    @PostMapping("/editer/{id}")
    public String modifier(@PathVariable UUID id, @ModelAttribute Partie partie) {
        partie.setId(id);
        partieService.save(partie);
        return "redirect:/parties";
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable UUID id) {
        partieService.deleteById(id);
        return "redirect:/parties";
    }
} 