package com.rpg.RPGestionnaire.service;

import com.rpg.RPGestionnaire.entity.StatJoueur;
import com.rpg.RPGestionnaire.repository.StatJoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StatJoueurService {
    @Autowired
    private StatJoueurRepository statJoueurRepository;

    public List<StatJoueur> findAll() {
        return statJoueurRepository.findAll();
    }

    public Optional<StatJoueur> findById(UUID id) {
        return statJoueurRepository.findById(id);
    }

    public StatJoueur save(StatJoueur statJoueur) {
        return statJoueurRepository.save(statJoueur);
    }

    public void deleteById(UUID id) {
        statJoueurRepository.deleteById(id);
    }
} 