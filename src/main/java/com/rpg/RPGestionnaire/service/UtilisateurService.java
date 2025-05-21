package com.rpg.RPGestionnaire.service;

import com.rpg.RPGestionnaire.entity.Utilisateur;
import com.rpg.RPGestionnaire.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> findById(UUID id) {
        return utilisateurRepository.findById(id);
    }

    public Utilisateur save(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteById(UUID id) {
        utilisateurRepository.deleteById(id);
    }
} 