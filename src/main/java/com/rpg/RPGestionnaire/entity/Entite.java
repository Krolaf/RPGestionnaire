package com.rpg.RPGestionnaire.entity;

import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @ManyToOne
    private Utilisateur createur;
    @OneToMany(mappedBy = "entite")
    private List<StatEntite> stats;

    // Getters et Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }

    public Utilisateur getCreateur() {
        return createur;
    }

    public void setCreateur(Utilisateur createur) {
        this.createur = createur;
    }

    public List<StatEntite> getStats() {
        return stats;
    }

    public void setStats(List<StatEntite> stats) {
        this.stats = stats;
    }
} 