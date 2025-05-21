package com.rpg.RPGestionnaire.entity;

import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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