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

    public StatutPartie getStatut() {
        return statut;
    }

    public void setStatut(StatutPartie statut) {
        this.statut = statut;
    }

    public Utilisateur getMj() {
        return mj;
    }

    public void setMj(Utilisateur mj) {
        this.mj = mj;
    }
} 