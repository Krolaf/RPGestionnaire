package com.rpg.RPGestionnaire.repository;

import com.rpg.RPGestionnaire.entity.StatJoueur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface StatJoueurRepository extends JpaRepository<StatJoueur, UUID> {
} 