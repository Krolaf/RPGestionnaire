package com.rpg.RPGestionnaire.controller;

import com.rpg.RPGestionnaire.service.HistoriqueActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/historique")
public class AdminHistoriqueController {
    @Autowired
    private HistoriqueActionService historiqueActionService;

    @GetMapping
    public String liste(Model model) {
        model.addAttribute("actions", historiqueActionService.findAll());
        return "admin/historique/liste";
    }
} 