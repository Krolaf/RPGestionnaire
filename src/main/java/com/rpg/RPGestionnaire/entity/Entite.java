package com.rpg.RPGestionnaire.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Entite {
    @Id
    @GeneratedValue
    private UUID id;
    private String titre;
    private String description;
    private String imageUrl;
    @ManyToOne
    private Partie partie;
    @OneToMany(mappedBy = "entite")
    private List<StatEntite> stats;
    // Getters, setters, constructeurs
} 