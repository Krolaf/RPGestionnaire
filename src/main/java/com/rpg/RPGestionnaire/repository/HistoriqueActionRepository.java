package com.rpg.RPGestionnaire.repository;

import com.rpg.RPGestionnaire.entity.HistoriqueAction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface HistoriqueActionRepository extends JpaRepository<HistoriqueAction, UUID> {
} 