package com.rpg.RPGestionnaire.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nom;

    private Integer valeur;

    @ManyToOne
    @JoinColumn(name = "fiche_personnage_id")
    private FichePersonnage fichePersonnage;

    @ManyToOne
    @JoinColumn(name = "entite_id")
    private Entite entite;

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public FichePersonnage getFichePersonnage() {
        return fichePersonnage;
    }

    public void setFichePersonnage(FichePersonnage fichePersonnage) {
        this.fichePersonnage = fichePersonnage;
    }

    public Entite getEntite() {
        return entite;
    }

    public void setEntite(Entite entite) {
        this.entite = entite;
    }

    public Integer getValeur() {
        return valeur;
    }

    public void setValeur(Integer valeur) {
        this.valeur = valeur;
    }
} 