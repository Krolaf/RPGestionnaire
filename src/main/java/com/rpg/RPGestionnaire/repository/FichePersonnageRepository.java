package com.rpg.RPGestionnaire.repository;

import com.rpg.RPGestionnaire.entity.FichePersonnage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface FichePersonnageRepository extends JpaRepository<FichePersonnage, UUID> {
} 