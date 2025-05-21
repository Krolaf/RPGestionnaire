package com.rpg.RPGestionnaire.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class StatEntite {
    @Id
    @GeneratedValue
    private UUID id;
    private String nom;
    private int valeur;
    @ManyToOne
    private Entite entite;
    // Getters, setters, constructeurs
} 