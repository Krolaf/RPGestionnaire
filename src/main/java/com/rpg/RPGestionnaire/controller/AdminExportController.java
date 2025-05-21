package com.rpg.RPGestionnaire.controller;

import com.rpg.RPGestionnaire.entity.Export;
import com.rpg.RPGestionnaire.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Controller
@RequestMapping("/admin/exports")
public class AdminExportController {
    @Autowired
    private ExportService exportService;

    @GetMapping
    public String liste(Model model) {
        model.addAttribute("exports", exportService.findAll());
        return "admin/export/liste";
    }

    @GetMapping("/ajouter")
    public String formulaireAjout(Model model) {
        model.addAttribute("export", new Export());
        return "admin/export/formulaire";
    }

    @PostMapping("/ajouter")
    public String enregistrer(@ModelAttribute Export export) {
        exportService.save(export);
        return "redirect:/admin/exports";
    }

    @GetMapping("/editer/{id}")
    public String formulaireEdition(@PathVariable UUID id, Model model) {
        Export export = exportService.findById(id).orElseThrow();
        model.addAttribute("export", export);
        return "admin/export/formulaire";
    }

    @PostMapping("/editer/{id}")
    public String modifier(@PathVariable UUID id, @ModelAttribute Export export) {
        export.setId(id);
        exportService.save(export);
        return "redirect:/admin/exports";
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable UUID id) {
        exportService.deleteById(id);
        return "redirect:/admin/exports";
    }
} 