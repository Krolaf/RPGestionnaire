package com.rpg.RPGestionnaire.repository;

import com.rpg.RPGestionnaire.entity.FichePersonnage;
import com.rpg.RPGestionnaire.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;

public interface FichePersonnageRepository extends JpaRepository<FichePersonnage, UUID> {
    List<FichePersonnage> findByUtilisateur_Pseudo(String pseudo);
} 