package com.rpg.RPGestionnaire.repository;

import com.rpg.RPGestionnaire.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, UUID> {
} 