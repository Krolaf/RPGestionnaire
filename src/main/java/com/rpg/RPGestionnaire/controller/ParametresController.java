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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/parametres", "/admin/parametres"})
public class ParametresController {
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private boolean isAdmin(Authentication auth) {
        return auth != null && auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    @GetMapping
    public String afficherParametres(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Utilisateur utilisateur = utilisateurService.findByPseudo(username).orElseThrow();
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("isAdmin", isAdmin(auth));
        return isAdmin(auth) ? "admin/parametres" : "parametres";
    }

    @PostMapping("/modifier")
    public String modifierCompte(@RequestParam String email, 
                               @RequestParam String password, 
                               Model model,
                               RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Utilisateur utilisateur = utilisateurService.findByPseudo(username).orElseThrow();
        
        utilisateur.setEmail(email);
        if (password != null && !password.isBlank()) {
            utilisateur.setPasswordHash(passwordEncoder.encode(password));
        }
        utilisateurService.save(utilisateur);
        
        redirectAttributes.addFlashAttribute("success", "Compte modifié avec succès.");
        return "redirect:/parametres";
    }

    @PostMapping("/supprimer")
    public String supprimerCompte(@RequestParam String passwordConfirm, 
                                @RequestParam String confirmation, 
                                Model model,
                                RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Utilisateur utilisateur = utilisateurService.findByPseudo(username).orElseThrow();
        
        if (!utilisateurService.verifyPassword(utilisateur, passwordConfirm) || 
            !"detruire le compte".equalsIgnoreCase(confirmation.trim())) {
            redirectAttributes.addFlashAttribute("error", "Confirmation incorrecte ou mot de passe invalide.");
            return "redirect:/parametres";
        }
        
        utilisateurService.deleteById(utilisateur.getId());
        SecurityContextHolder.clearContext();
        return "redirect:/login?deleted";
    }
} 