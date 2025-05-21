package com.rpg.RPGestionnaire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front")
public class FrontController {
    @GetMapping
    public String accueil() {
        return "front/index";
    }
} 