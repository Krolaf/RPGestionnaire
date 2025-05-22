package com.rpg.RPGestionnaire.controller;

import com.rpg.RPGestionnaire.service.PartieService;
import com.rpg.RPGestionnaire.service.EntiteService;
import com.rpg.RPGestionnaire.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rpg.RPGestionnaire.entity.Partie;
import com.rpg.RPGestionnaire.entity.Entite;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.UUID;
import com.rpg.RPGestionnaire.entity.Utilisateur;

@Controller
@RequestMapping("/front/gm")
public class GmController {
    @Autowired
    private PartieService partieService;
    @Autowired
    private EntiteService entiteService;
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/dashboard")
    public String gmDashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authorities: " + auth.getAuthorities());
        String username = auth.getName();
        model.addAttribute("games", partieService.findByGmUsername(username));
        model.addAttribute("entities", entiteService.findByGmUsername(username));
        model.addAttribute("session", java.util.Map.of("user", java.util.Map.of("username", username)));
        model.addAttribute("newPartie", new Partie());
        model.addAttribute("newEntite", new Entite());
        return "front/gm/dashboard";
    }

    // CRUD PARTIE
    @GetMapping("/parties/ajouter")
    public String formulaireAjoutPartie(Model model) {
        model.addAttribute("partie", new Partie());
        return "front/gm/partie-formulaire";
    }

    @PostMapping("/parties/ajouter")
    public String ajouterPartie(@ModelAttribute Partie partie) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Utilisateur mj = utilisateurService.findByPseudo(username).orElseThrow();
        partie.setMj(mj);
        partieService.save(partie);
        return "redirect:/front/gm/dashboard?tab=games";
    }

    @GetMapping("/parties/editer/{id}")
    public String formulaireEditionPartie(@PathVariable UUID id, Model model) {
        Partie partie = partieService.findById(id).orElseThrow();
        model.addAttribute("partie", partie);
        return "front/gm/partie-formulaire";
    }

    @PostMapping("/parties/editer/{id}")
    public String editerPartie(@PathVariable UUID id, @ModelAttribute Partie partie) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Utilisateur mj = utilisateurService.findByPseudo(username).orElseThrow();

        Partie existing = partieService.findById(id).orElseThrow();
        existing.setTitre(partie.getTitre());
        existing.setDescription(partie.getDescription());
        existing.setImageUrl(partie.getImageUrl());
        existing.setStatut(partie.getStatut());
        existing.setMj(mj);

        partieService.save(existing);
        return "redirect:/front/gm/dashboard?tab=games";
    }

    @GetMapping("/parties/supprimer/{id}")
    public String supprimerPartie(@PathVariable UUID id) {
        partieService.deleteById(id);
        return "redirect:/front/gm/dashboard";
    }

    // CRUD ENTITE
    @GetMapping("/entites/ajouter")
    public String formulaireAjoutEntite(Model model) {
        model.addAttribute("entite", new Entite());
        return "front/gm/entite-formulaire";
    }

    @PostMapping("/entites/ajouter")
    public String ajouterEntite(@ModelAttribute Entite entite) {
        entiteService.save(entite);
        return "redirect:/front/gm/dashboard?tab=entities";
    }

    @GetMapping("/entites/editer/{id}")
    public String formulaireEditionEntite(@PathVariable UUID id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("[DEBUG] Authorities (edit entite): " + auth.getAuthorities());
        System.out.println("[DEBUG] Username (edit entite): " + auth.getName());
        Entite entite = entiteService.findById(id).orElseThrow();
        model.addAttribute("entite", entite);
        return "front/gm/entite-formulaire";
    }

    @PostMapping("/entites/editer/{id}")
    public String editerEntite(@PathVariable UUID id, @ModelAttribute Entite entite) {
        entite.setId(id);
        entiteService.save(entite);
        return "redirect:/front/gm/dashboard?tab=entities";
    }

    @GetMapping("/entites/supprimer/{id}")
    public String supprimerEntite(@PathVariable UUID id) {
        entiteService.deleteById(id);
        return "redirect:/front/gm/dashboard";
    }
} 