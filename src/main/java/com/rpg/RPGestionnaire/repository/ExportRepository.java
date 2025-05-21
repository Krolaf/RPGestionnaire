package com.rpg.RPGestionnaire.repository;

import com.rpg.RPGestionnaire.entity.Export;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ExportRepository extends JpaRepository<Export, UUID> {
} 