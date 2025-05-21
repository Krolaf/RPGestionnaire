package com.rpg.RPGestionnaire.repository;

import com.rpg.RPGestionnaire.entity.Partie;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PartieRepository extends JpaRepository<Partie, UUID> {
} 