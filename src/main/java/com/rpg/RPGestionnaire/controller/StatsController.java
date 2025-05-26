package com.rpg.RPGestionnaire.controller;

import com.rpg.RPGestionnaire.entity.*;
import com.rpg.RPGestionnaire.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/stats")
public class StatsController {
    @Autowired
    private StatService statService;
    @Autowired
    private FichePersonnageService fichePersonnageService;
    @Autowired
    private EntiteService entiteService;

    // ADMIN : Gestion globale des stats
    @GetMapping("/admin/stats")
    public String listeAdmin(Model model, Authentication auth) {
        if (hasRole(auth, "ROLE_ADMIN")) {
            model.addAttribute("stats", statService.findAll());
            return "admin/stats/liste";
        }
        return "error/403";
    }

    // ADMIN : Ajout de stats à une fiche
    @PostMapping("/fiche-personnage/{fichePersonnageId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GM')")
    public String addStatToFichePersonnage(@PathVariable UUID fichePersonnageId, 
                                         @RequestParam String nom,
                                         @RequestParam Integer valeur) {
        FichePersonnage fichePersonnage = fichePersonnageService.findById(fichePersonnageId).orElse(null);
        if (fichePersonnage != null) {
            Stat stat = new Stat();
            stat.setNom(nom);
            stat.setValeur(valeur);
            stat.setFichePersonnage(fichePersonnage);
            statService.save(stat);
        }
        return "redirect:/fiche-personnage/" + fichePersonnageId;
    }

    // ADMIN : Ajout de stats à une entité
    @PostMapping("/entite/{entiteId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GM')")
    public String addStatToEntite(@PathVariable UUID entiteId,
                                 @RequestParam String nom,
                                 @RequestParam Integer valeur) {
        Entite entite = entiteService.findById(entiteId).orElse(null);
        if (entite != null) {
            Stat stat = new Stat();
            stat.setNom(nom);
            stat.setValeur(valeur);
            stat.setEntite(entite);
            statService.save(stat);
        }
        return "redirect:/entite/" + entiteId;
    }

    // JOUEUR : Modification des stats de sa fiche
    @PostMapping("/front/player/fiches/{ficheId}/stats/{statId}")
    public String modifierStatFicheJoueur(@PathVariable UUID ficheId, @PathVariable UUID statId, 
                                        @ModelAttribute Stat stat, Authentication auth) {
        if (hasRole(auth, "ROLE_JOUEUR")) {
            String username = auth.getName();
            FichePersonnage fiche = fichePersonnageService.findById(ficheId).orElseThrow();
            
            if (fiche.getUtilisateur() != null && fiche.getUtilisateur().getPseudo().equals(username)) {
                Stat existingStat = statService.findById(statId).orElseThrow();
                existingStat.setValeur(stat.getValeur());
                statService.save(existingStat);
                return "redirect:/front/player/fiches/editer/" + ficheId;
            }
        }
        return "error/403";
    }

    // MJ : Modification des stats d'une fiche dans sa partie
    @PostMapping("/gm/partie/{partieId}/fiche/{ficheId}/stats/{statId}")
    public String modifierStatFicheMj(@PathVariable UUID partieId, @PathVariable UUID ficheId, 
                                    @PathVariable UUID statId, @ModelAttribute Stat stat, Authentication auth) {
        if (hasRole(auth, "ROLE_MJ")) {
            // TODO : Vérifier que le MJ a le droit sur la partie
            Stat existingStat = statService.findById(statId).orElseThrow();
            existingStat.setValeur(stat.getValeur());
            statService.save(existingStat);
            return "redirect:/gm/partie/" + partieId + "/fiche/" + ficheId;
        }
        return "error/403";
    }

    // MJ : Modification des stats d'une entité dans sa partie
    @PostMapping("/gm/partie/{partieId}/entite/{entiteId}/stats/{statId}")
    public String modifierStatEntiteMj(@PathVariable UUID partieId, @PathVariable UUID entiteId, 
                                     @PathVariable UUID statId, @ModelAttribute Stat stat, Authentication auth) {
        if (hasRole(auth, "ROLE_MJ")) {
            // TODO : Vérifier que le MJ a le droit sur la partie
            Stat existingStat = statService.findById(statId).orElseThrow();
            existingStat.setValeur(stat.getValeur());
            statService.save(existingStat);
            return "redirect:/gm/partie/" + partieId + "/entite/" + entiteId;
        }
        return "error/403";
    }

    // Suppression de stat (admin uniquement)
    @PostMapping("/delete/{statId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GM')")
    public String deleteStat(@PathVariable UUID statId) {
        Stat stat = statService.findById(statId).orElse(null);
        if (stat != null) {
            UUID redirectId = null;
            if (stat.getFichePersonnage() != null) {
                redirectId = stat.getFichePersonnage().getId();
            } else if (stat.getEntite() != null) {
                redirectId = stat.getEntite().getId();
            }
            
            statService.deleteById(statId);
            
            if (redirectId != null) {
                return "redirect:/fiche-personnage/" + redirectId;
            }
        }
        return "redirect:/";
    }

    private boolean hasRole(Authentication auth, String role) {
        return auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(role));
    }
} 