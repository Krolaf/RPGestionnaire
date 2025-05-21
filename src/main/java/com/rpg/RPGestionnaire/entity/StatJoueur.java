package com.rpg.RPGestionnaire.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class StatJoueur {
    @Id
    @GeneratedValue
    private UUID id;
    private String nom;
    private int valeur;
    @ManyToOne
    private FichePersonnage fichePersonnage;
    // Getters, setters, constructeurs
} 