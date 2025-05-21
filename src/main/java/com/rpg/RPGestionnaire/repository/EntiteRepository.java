package com.rpg.RPGestionnaire.repository;

import com.rpg.RPGestionnaire.entity.Entite;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface EntiteRepository extends JpaRepository<Entite, UUID> {
} 