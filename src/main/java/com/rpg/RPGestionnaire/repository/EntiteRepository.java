package com.rpg.RPGestionnaire.repository;

import com.rpg.RPGestionnaire.entity.Entite;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;

public interface EntiteRepository extends JpaRepository<Entite, UUID> {
    List<Entite> findByCreateurPseudo(String pseudo);
    List<Entite> findByPartieId(UUID partieId);
} 