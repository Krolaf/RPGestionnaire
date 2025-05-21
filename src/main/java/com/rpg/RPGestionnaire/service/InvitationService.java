package com.rpg.RPGestionnaire.service;

import com.rpg.RPGestionnaire.entity.Invitation;
import com.rpg.RPGestionnaire.repository.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class InvitationService {
    @Autowired
    private InvitationRepository invitationRepository;

    public List<Invitation> findAll() {
        return invitationRepository.findAll();
    }

    public Optional<Invitation> findById(UUID id) {
        return invitationRepository.findById(id);
    }

    public Invitation save(Invitation invitation) {
        return invitationRepository.save(invitation);
    }

    public void deleteById(UUID id) {
        invitationRepository.deleteById(id);
    }
} 