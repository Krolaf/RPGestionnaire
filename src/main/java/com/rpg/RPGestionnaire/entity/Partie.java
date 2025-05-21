package com.rpg.RPGestionnaire.entity;

import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Partie {
    @Id
    @GeneratedValue
    private UUID id;
    private String titre;
    private String description;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private StatutPartie statut;
    @ManyToOne
    private Utilisateur mj;
    // Getters, setters, constructeurs
} 