package com.rpg.RPGestionnaire.controller;

import com.rpg.RPGestionnaire.entity.Entite;
import com.rpg.RPGestionnaire.service.EntiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Controller
@RequestMapping("/admin/entites")
public class AdminEntiteController {
    @Autowired
    private EntiteService entiteService;

    @GetMapping
    public String liste(Model model) {
        model.addAttribute("entites", entiteService.findAll());
        return "admin/entite/liste";
    }

    @GetMapping("/ajouter")
    public String formulaireAjout(Model model) {
        model.addAttribute("entite", new Entite());
        return "admin/entite/formulaire";
    }

    @PostMapping("/ajouter")
    public String enregistrer(@ModelAttribute Entite entite) {
        entiteService.save(entite);
        return "redirect:/admin/entites";
    }

    @GetMapping("/editer/{id}")
    public String formulaireEdition(@PathVariable UUID id, Model model) {
        Entite entite = entiteService.findById(id).orElseThrow();
        model.addAttribute("entite", entite);
        return "admin/entite/formulaire";
    }

    @PostMapping("/editer/{id}")
    public String modifier(@PathVariable UUID id, @ModelAttribute Entite entite) {
        entite.setId(id);
        entiteService.save(entite);
        return "redirect:/admin/entites";
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable UUID id) {
        entiteService.deleteById(id);
        return "redirect:/admin/entites";
    }
} 