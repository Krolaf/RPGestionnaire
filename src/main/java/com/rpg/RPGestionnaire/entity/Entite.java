package com.rpg.RPGestionnaire.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Entite {
    @Id
    @GeneratedValue
    private UUID id;
    private String titre;
    private String description;
    private String imageUrl;
    // Getters, setters, constructeurs
} 