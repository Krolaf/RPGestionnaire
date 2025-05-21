package com.rpg.RPGestionnaire.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class HistoriqueAction {
    @Id
    @GeneratedValue
    private UUID id;
    private UUID utilisateurId;
    private String typeAction; // INVITATION, EXPORT, CREATION_PARTIE
    private String details;    // Infos complémentaires (ex: email invité, type d'export, titre de partie...)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAction;
} 