package com.rpg.RPGestionnaire.controller;

import com.rpg.RPGestionnaire.entity.FichePersonnage;
import com.rpg.RPGestionnaire.entity.Utilisateur;
import com.rpg.RPGestionnaire.service.FichePersonnageService;
import com.rpg.RPGestionnaire.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@Controller
public class FichePersonnageController {
    @Autowired
    private FichePersonnageService fichePersonnageService;
    @Autowired
    private UtilisateurService utilisateurService;

    // ADMIN : CRUD global
    @GetMapping("/admin/fiches")
    public String listeAdmin(Model model, Authentication auth) {
        if (hasRole(auth, "ROLE_ADMIN")) {
            model.addAttribute("fiches", fichePersonnageService.findAll());
            return "admin/fiche/liste";
        }
        return "error/403";
    }

    @GetMapping("/admin/fiches/ajouter")
    public String formulaireAjoutAdmin(Model model, Authentication auth) {
        if (hasRole(auth, "ROLE_ADMIN")) {
            FichePersonnage fiche = new FichePersonnage();
            model.addAttribute("fichePersonnage", fiche);
            model.addAttribute("utilisateurs", utilisateurService.findAll());
            return "admin/fiche/formulaire";
        }
        return "error/403";
    }

    @PostMapping("/admin/fiches/ajouter")
    public String enregistrerAdmin(@ModelAttribute FichePersonnage fichePersonnage, @RequestParam("utilisateur") UUID utilisateurId, Authentication auth) {
        if (hasRole(auth, "ROLE_ADMIN")) {
            fichePersonnage.setUtilisateur(utilisateurService.findById(utilisateurId).orElse(null));
            fichePersonnageService.save(fichePersonnage);
            return "redirect:/admin/fiches";
        }
        return "error/403";
    }

    @GetMapping("/admin/fiches/editer/{id}")
    public String formulaireEditionAdmin(@PathVariable UUID id, Model model, Authentication auth) {
        if (hasRole(auth, "ROLE_ADMIN")) {
            FichePersonnage fiche = fichePersonnageService.findById(id).orElseThrow();
            model.addAttribute("fichePersonnage", fiche);
            model.addAttribute("utilisateurs", utilisateurService.findAll());
            return "admin/fiche/formulaire";
        }
        return "error/403";
    }

    @PostMapping("/admin/fiches/editer/{id}")
    public String modifierAdmin(@PathVariable UUID id, @ModelAttribute FichePersonnage fichePersonnage, @RequestParam("utilisateur") UUID utilisateurId, Authentication auth) {
        if (hasRole(auth, "ROLE_ADMIN")) {
            fichePersonnage.setId(id);
            fichePersonnage.setUtilisateur(utilisateurService.findById(utilisateurId).orElse(null));
            fichePersonnageService.save(fichePersonnage);
            return "redirect:/admin/fiches";
        }
        return "error/403";
    }

    @GetMapping("/admin/fiches/supprimer/{id}")
    public String supprimerAdmin(@PathVariable UUID id, Authentication auth) {
        if (hasRole(auth, "ROLE_ADMIN")) {
            fichePersonnageService.deleteById(id);
            return "redirect:/admin/fiches";
        }
        return "error/403";
    }

    // JOUEUR : CRUD sur ses propres fiches depuis dashboard
    @GetMapping("/front/player/fiches")
    public String listeJoueur(Model model, Authentication auth) {
        if (hasRole(auth, "ROLE_JOUEUR")) {
            String username = auth.getName();
            List<FichePersonnage> fiches = fichePersonnageService.findByUtilisateurPseudo(username);
            model.addAttribute("fiches", fiches);
            return "front/player/fiches-liste";
        }
        return "error/403";
    }

    @GetMapping("/front/player/fiches/editer/{id}")
    public String formulaireEditionJoueur(@PathVariable UUID id, Model model, Authentication auth) {
        if (hasRole(auth, "ROLE_JOUEUR")) {
            String username = auth.getName();
            FichePersonnage fiche = fichePersonnageService.findById(id).orElseThrow();
            if (fiche.getUtilisateur() != null && fiche.getUtilisateur().getPseudo().equals(username)) {
                model.addAttribute("fichePersonnage", fiche);
                return "front/player/fiche-formulaire";
            }
        }
        return "error/403";
    }

    @PostMapping("/front/player/fiches/editer/{id}")
    public String modifierJoueur(@PathVariable UUID id, @ModelAttribute FichePersonnage fichePersonnage, Authentication auth) {
        if (hasRole(auth, "ROLE_JOUEUR")) {
            String username = auth.getName();
            FichePersonnage fiche = fichePersonnageService.findById(id).orElseThrow();
            if (fiche.getUtilisateur() != null && fiche.getUtilisateur().getPseudo().equals(username)) {
                fichePersonnage.setId(id);
                fichePersonnage.setUtilisateur(fiche.getUtilisateur());
                fichePersonnageService.save(fichePersonnage);
                return "redirect:/front/player/fiches";
            }
        }
        return "error/403";
    }

    // JOUEUR : édition de la fiche de la partie courante
    @GetMapping("/front/player/partie/{partieId}/fiche/{ficheId}")
    public String formulaireEditionJoueurPartie(@PathVariable UUID partieId, @PathVariable UUID ficheId, Model model, Authentication auth) {
        if (hasRole(auth, "ROLE_JOUEUR")) {
            String username = auth.getName();
            FichePersonnage fiche = fichePersonnageService.findById(ficheId).orElseThrow();
            if (fiche.getUtilisateur() != null && fiche.getUtilisateur().getPseudo().equals(username)) {
                // TODO : vérifier que la fiche appartient bien à la partie partieId
                model.addAttribute("fichePersonnage", fiche);
                return "front/player/fiche-formulaire";
            }
        }
        return "error/403";
    }

    // MJ : édition d'une fiche dans une partie
    @GetMapping("/gm/partie/{partieId}/fiche/{ficheId}")
    public String formulaireEditionMjPartie(@PathVariable UUID partieId, @PathVariable UUID ficheId, Model model, Authentication auth) {
        if (hasRole(auth, "ROLE_MJ")) {
            // TODO : vérifier que le MJ a bien le droit sur la partie partieId et la fiche ficheId
            FichePersonnage fiche = fichePersonnageService.findById(ficheId).orElseThrow();
            model.addAttribute("fichePersonnage", fiche);
            return "gm/fiche-formulaire";
        }
        return "error/403";
    }

    // MJ : liste des fiches d'une partie
    @GetMapping("/gm/partie/{partieId}/fiches")
    public String listeFichesMjPartie(@PathVariable UUID partieId, Model model, Authentication auth) {
        if (hasRole(auth, "ROLE_MJ")) {
            // TODO : filtrer les fiches de la partie partieId
            List<FichePersonnage> fiches = fichePersonnageService.findByPartieId(partieId);
            model.addAttribute("fiches", fiches);
            return "gm/fiches-liste";
        }
        return "error/403";
    }

    private boolean hasRole(Authentication auth, String role) {
        return auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(role));
    }
} 