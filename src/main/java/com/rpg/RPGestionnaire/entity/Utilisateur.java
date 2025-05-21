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

    // Getters et Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Partie> getPartiesMaitrisees() {
        return partiesMaitrisees;
    }

    public void setPartiesMaitrisees(List<Partie> partiesMaitrisees) {
        this.partiesMaitrisees = partiesMaitrisees;
    }

    public List<FichePersonnage> getFichesPersonnage() {
        return fichesPersonnage;
    }

    public void setFichesPersonnage(List<FichePersonnage> fichesPersonnage) {
        this.fichesPersonnage = fichesPersonnage;
    }

    public List<Export> getExports() {
        return exports;
    }

    public void setExports(List<Export> exports) {
        this.exports = exports;
    }
} 