package com.rpg.RPGestionnaire.entity;

import jakarta.persistence.*;
import java.util.*;

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
    // Getters, setters, constructeurs
} 