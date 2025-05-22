package com.rpg.RPGestionnaire.controller;

import com.rpg.RPGestionnaire.entity.Utilisateur;
import com.rpg.RPGestionnaire.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/parametres")
public class ParametresController {
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String afficherParametres(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Utilisateur utilisateur = utilisateurService.findByPseudo(username).orElseThrow();
        model.addAttribute("utilisateur", utilisateur);
        return "parametres";
    }

    @PostMapping("/modifier")
    public String modifierCompte(@RequestParam String email, @RequestParam String password, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Utilisateur utilisateur = utilisateurService.findByPseudo(username).orElseThrow();
        utilisateur.setEmail(email);
        if (password != null && !password.isBlank()) {
            utilisateur.setPasswordHash(passwordEncoder.encode(password));
        }
        utilisateurService.save(utilisateur);
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("success", "Compte modifié avec succès.");
        return "parametres";
    }

    @PostMapping("/supprimer")
    public String supprimerCompte(@RequestParam String passwordConfirm, @RequestParam String confirmation, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Utilisateur utilisateur = utilisateurService.findByPseudo(username).orElseThrow();
        if (!passwordEncoder.matches(passwordConfirm, utilisateur.getPasswordHash()) || !"detruire le compte".equalsIgnoreCase(confirmation.trim())) {
            model.addAttribute("utilisateur", utilisateur);
            model.addAttribute("error", "Confirmation incorrecte ou mot de passe invalide.");
            return "parametres";
        }
        utilisateurService.deleteById(utilisateur.getId());
        SecurityContextHolder.clearContext();
        return "redirect:/login?deleted";
    }
} 