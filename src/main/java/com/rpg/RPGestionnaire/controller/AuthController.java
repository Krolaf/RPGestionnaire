package com.rpg.RPGestionnaire.controller;

import com.rpg.RPGestionnaire.entity.Role;
import com.rpg.RPGestionnaire.entity.Utilisateur;
import com.rpg.RPGestionnaire.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        model.addAttribute("roles", new Role[]{Role.MJ, Role.Joueur});
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Utilisateur utilisateur, @RequestParam String confirmPassword, Model model) {
        System.out.println("Tentative inscription : " + utilisateur.getPseudo() + " / " + utilisateur.getEmail());
        // Champs obligatoires
        if (utilisateur.getPseudo() == null || utilisateur.getPseudo().isBlank() ||
            utilisateur.getEmail() == null || utilisateur.getEmail().isBlank() ||
            utilisateur.getPasswordHash() == null || utilisateur.getPasswordHash().isBlank() ||
            utilisateur.getRole() == null) {
            model.addAttribute("error", "Tous les champs sont obligatoires.");
            model.addAttribute("roles", new Role[]{Role.MJ, Role.Joueur});
            return "auth/register";
        }
        // Pseudo ≠ email
        if (utilisateur.getPseudo().equalsIgnoreCase(utilisateur.getEmail())) {
            model.addAttribute("error", "Le pseudo et l'email doivent être différents.");
            model.addAttribute("roles", new Role[]{Role.MJ, Role.Joueur});
            return "auth/register";
        }
        // Unicité pseudo/email
        if (utilisateurService.existsByPseudo(utilisateur.getPseudo())) {
            model.addAttribute("error", "Ce pseudo est déjà utilisé.");
            model.addAttribute("roles", new Role[]{Role.MJ, Role.Joueur});
            return "auth/register";
        }
        if (utilisateurService.existsByEmail(utilisateur.getEmail())) {
            model.addAttribute("error", "Cet email est déjà utilisé.");
            model.addAttribute("roles", new Role[]{Role.MJ, Role.Joueur});
            return "auth/register";
        }
        // Vérification des mots de passe
        if (!utilisateur.getPasswordHash().equals(confirmPassword)) {
            model.addAttribute("error", "Les mots de passe ne correspondent pas.");
            model.addAttribute("roles", new Role[]{Role.MJ, Role.Joueur});
            return "auth/register";
        }
        // Hash du mot de passe
        utilisateur.setPasswordHash(passwordEncoder.encode(utilisateur.getPasswordHash()));
        utilisateurService.save(utilisateur);
        return "redirect:/login?registerSuccess";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login";
    }
} 