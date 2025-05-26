package com.rpg.RPGestionnaire.repository;

import com.rpg.RPGestionnaire.entity.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface StatRepository extends JpaRepository<Stat, UUID> {
    List<Stat> findByFichePersonnageId(UUID ficheId);
    List<Stat> findByEntiteId(UUID entiteId);
} 