package com.rpg.RPGestionnaire.repository;

import com.rpg.RPGestionnaire.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, UUID> {
    Optional<Utilisateur> findByPseudo(String pseudo);
    Optional<Utilisateur> findByEmail(String email);
} 