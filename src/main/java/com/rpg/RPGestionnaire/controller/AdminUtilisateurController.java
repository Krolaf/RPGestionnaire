package com.rpg.RPGestionnaire.controller;

import com.rpg.RPGestionnaire.entity.Utilisateur;
import com.rpg.RPGestionnaire.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Controller
@RequestMapping("/admin/utilisateurs")
public class AdminUtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public String liste(Model model) {
        model.addAttribute("utilisateurs", utilisateurService.findAll());
        return "admin/utilisateur/liste";
    }

    @GetMapping("/ajouter")
    public String formulaireAjout(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "admin/utilisateur/formulaire";
    }

    @PostMapping("/ajouter")
    public String enregistrer(@ModelAttribute Utilisateur utilisateur) {
        utilisateurService.save(utilisateur);
        return "redirect:/admin/utilisateurs";
    }

    @GetMapping("/editer/{id}")
    public String formulaireEdition(@PathVariable UUID id, Model model) {
        Utilisateur utilisateur = utilisateurService.findById(id).orElseThrow();
        model.addAttribute("utilisateur", utilisateur);
        return "admin/utilisateur/formulaire";
    }

    @PostMapping("/editer/{id}")
    public String modifier(@PathVariable UUID id, @ModelAttribute Utilisateur utilisateur) {
        utilisateur.setId(id);
        utilisateurService.save(utilisateur);
        return "redirect:/admin/utilisateurs";
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable UUID id) {
        utilisateurService.deleteById(id);
        return "redirect:/admin/utilisateurs";
    }
} 