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

    // Getters et Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomPersonnage() {
        return nomPersonnage;
    }

    public void setNomPersonnage(String nomPersonnage) {
        this.nomPersonnage = nomPersonnage;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<StatJoueur> getStats() {
        return stats;
    }

    public void setStats(List<StatJoueur> stats) {
        this.stats = stats;
    }
} 