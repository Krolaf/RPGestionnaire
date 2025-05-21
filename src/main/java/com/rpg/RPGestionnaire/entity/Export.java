package com.rpg.RPGestionnaire.entity;

import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Export {
    @Id
    @GeneratedValue
    private UUID id;
    @Enumerated(EnumType.STRING)
    private ExportType type;
    private String url;
    private Date date;
    @ManyToOne
    private Partie partie;
    @ManyToOne
    private Utilisateur utilisateur;
    // Getters, setters, constructeurs
} 