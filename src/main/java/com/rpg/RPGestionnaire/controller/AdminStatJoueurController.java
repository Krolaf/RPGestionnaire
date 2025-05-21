package com.rpg.RPGestionnaire.controller;

import com.rpg.RPGestionnaire.entity.StatJoueur;
import com.rpg.RPGestionnaire.service.StatJoueurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Controller
@RequestMapping("/admin/statjoueurs")
public class AdminStatJoueurController {
    @Autowired
    private StatJoueurService statJoueurService;

    @GetMapping
    public String liste(Model model) {
        model.addAttribute("statjoueurs", statJoueurService.findAll());
        return "admin/statjoueur/liste";
    }

    @GetMapping("/ajouter")
    public String formulaireAjout(Model model) {
        model.addAttribute("statJoueur", new StatJoueur());
        return "admin/statjoueur/formulaire";
    }

    @PostMapping("/ajouter")
    public String enregistrer(@ModelAttribute StatJoueur statJoueur) {
        statJoueurService.save(statJoueur);
        return "redirect:/admin/statjoueurs";
    }

    @GetMapping("/editer/{id}")
    public String formulaireEdition(@PathVariable UUID id, Model model) {
        StatJoueur statJoueur = statJoueurService.findById(id).orElseThrow();
        model.addAttribute("statJoueur", statJoueur);
        return "admin/statjoueur/formulaire";
    }

    @PostMapping("/editer/{id}")
    public String modifier(@PathVariable UUID id, @ModelAttribute StatJoueur statJoueur) {
        statJoueur.setId(id);
        statJoueurService.save(statJoueur);
        return "redirect:/admin/statjoueurs";
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable UUID id) {
        statJoueurService.deleteById(id);
        return "redirect:/admin/statjoueurs";
    }
} 