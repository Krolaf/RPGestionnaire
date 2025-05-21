package com.rpg.RPGestionnaire.controller;

import com.rpg.RPGestionnaire.entity.Invitation;
import com.rpg.RPGestionnaire.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Controller
@RequestMapping("/admin/invitations")
public class AdminInvitationController {
    @Autowired
    private InvitationService invitationService;

    @GetMapping
    public String liste(Model model) {
        model.addAttribute("invitations", invitationService.findAll());
        return "admin/invitation/liste";
    }

    @GetMapping("/ajouter")
    public String formulaireAjout(Model model) {
        model.addAttribute("invitation", new Invitation());
        return "admin/invitation/formulaire";
    }

    @PostMapping("/ajouter")
    public String enregistrer(@ModelAttribute Invitation invitation) {
        invitationService.save(invitation);
        return "redirect:/admin/invitations";
    }

    @GetMapping("/editer/{id}")
    public String formulaireEdition(@PathVariable UUID id, Model model) {
        Invitation invitation = invitationService.findById(id).orElseThrow();
        model.addAttribute("invitation", invitation);
        return "admin/invitation/formulaire";
    }

    @PostMapping("/editer/{id}")
    public String modifier(@PathVariable UUID id, @ModelAttribute Invitation invitation) {
        invitation.setId(id);
        invitationService.save(invitation);
        return "redirect:/admin/invitations";
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable UUID id) {
        invitationService.deleteById(id);
        return "redirect:/admin/invitations";
    }
} 