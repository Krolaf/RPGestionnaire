package com.rpg.RPGestionnaire.repository;

import com.rpg.RPGestionnaire.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface InvitationRepository extends JpaRepository<Invitation, UUID> {
} 