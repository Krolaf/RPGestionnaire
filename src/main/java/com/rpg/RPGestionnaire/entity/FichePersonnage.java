package com.rpg.RPGestionnaire.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class FichePersonnage {
    @Id
    @GeneratedValue
    private UUID id;
    private String nomPersonnage;
    private Date dateCreation;
    @ManyToOne
    private Utilisateur utilisateur;
    // Getters, setters, constructeurs
} 