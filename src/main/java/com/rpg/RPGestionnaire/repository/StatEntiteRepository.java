package com.rpg.RPGestionnaire.repository;

import com.rpg.RPGestionnaire.entity.StatEntite;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface StatEntiteRepository extends JpaRepository<StatEntite, UUID> {
} 