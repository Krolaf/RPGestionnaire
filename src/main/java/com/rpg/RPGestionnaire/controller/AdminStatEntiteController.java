package com.rpg.RPGestionnaire.controller;

import com.rpg.RPGestionnaire.entity.StatEntite;
import com.rpg.RPGestionnaire.service.StatEntiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Controller
@RequestMapping("/admin/statentites")
public class AdminStatEntiteController {
    @Autowired
    private StatEntiteService statEntiteService;

    @GetMapping
    public String liste(Model model) {
        model.addAttribute("statentites", statEntiteService.findAll());
        return "admin/statentite/liste";
    }

    @GetMapping("/ajouter")
    public String formulaireAjout(Model model) {
        model.addAttribute("statEntite", new StatEntite());
        return "admin/statentite/formulaire";
    }

    @PostMapping("/ajouter")
    public String enregistrer(@ModelAttribute StatEntite statEntite) {
        statEntiteService.save(statEntite);
        return "redirect:/admin/statentites";
    }

    @GetMapping("/editer/{id}")
    public String formulaireEdition(@PathVariable UUID id, Model model) {
        StatEntite statEntite = statEntiteService.findById(id).orElseThrow();
        model.addAttribute("statEntite", statEntite);
        return "admin/statentite/formulaire";
    }

    @PostMapping("/editer/{id}")
    public String modifier(@PathVariable UUID id, @ModelAttribute StatEntite statEntite) {
        statEntite.setId(id);
        statEntiteService.save(statEntite);
        return "redirect:/admin/statentites";
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable UUID id) {
        statEntiteService.deleteById(id);
        return "redirect:/admin/statentites";
    }
} 