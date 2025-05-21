package com.rpg.RPGestionnaire.entity;

import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FichePersonnage {
    @Id
    @GeneratedValue
    private UUID id;
    private String nomPersonnage;
    private Date dateCreation;
    @ManyToOne
    private Utilisateur utilisateur;
    @OneToMany(mappedBy = "fichePersonnage")
    private List<StatJoueur> stats;
    // Getters, setters, constructeurs
} 