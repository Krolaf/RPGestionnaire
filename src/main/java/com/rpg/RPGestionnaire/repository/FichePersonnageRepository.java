package com.rpg.RPGestionnaire.repository;

import com.rpg.RPGestionnaire.entity.FichePersonnage;
import com.rpg.RPGestionnaire.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.List;

@Repository
public interface FichePersonnageRepository extends JpaRepository<FichePersonnage, UUID> {
    List<FichePersonnage> findByUtilisateur_Pseudo(String pseudo);
    List<FichePersonnage> findByPartie_Id(UUID partieId);
} 