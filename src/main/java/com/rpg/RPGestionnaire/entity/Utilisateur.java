package com.rpg.RPGestionnaire.entity;

import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Utilisateur {
    @Id
    @GeneratedValue
    private UUID id;
    private String nom;
    private String prenom;
    private String email;
    private String pseudo;
    private String passwordHash;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "mj")
    private List<Partie> partiesMaitrisees;

    @OneToMany(mappedBy = "utilisateur")
    private List<FichePersonnage> fichesPersonnage;

    @OneToMany(mappedBy = "utilisateur")
    private List<Export> exports;

    // Getters, setters, constructeurs
} 