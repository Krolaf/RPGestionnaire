package com.rpg.RPGestionnaire.entity;

import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Invitation {
    @Id
    @GeneratedValue
    private UUID id;
    private String token;
    @Enumerated(EnumType.STRING)
    private TypeInvitation type;
    @Enumerated(EnumType.STRING)
    private MethodeInvitation methode;
    private boolean utilise;
    @ManyToOne
    private Partie partie;

    // Getters et Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TypeInvitation getType() {
        return type;
    }

    public void setType(TypeInvitation type) {
        this.type = type;
    }

    public MethodeInvitation getMethode() {
        return methode;
    }

    public void setMethode(MethodeInvitation methode) {
        this.methode = methode;
    }

    public boolean isUtilise() {
        return utilise;
    }

    public void setUtilise(boolean utilise) {
        this.utilise = utilise;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }
} 