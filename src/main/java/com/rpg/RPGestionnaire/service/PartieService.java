package com.rpg.RPGestionnaire.service;

import com.rpg.RPGestionnaire.entity.Partie;
import com.rpg.RPGestionnaire.repository.PartieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PartieService {
    @Autowired
    private PartieRepository partieRepository;

    public List<Partie> findAll() {
        return partieRepository.findAll();
    }

    public Optional<Partie> findById(UUID id) {
        return partieRepository.findById(id);
    }

    public Partie save(Partie partie) {
        return partieRepository.save(partie);
    }

    public void deleteById(UUID id) {
        partieRepository.deleteById(id);
    }

    public List<Partie> findByGmUsername(String username) {
        return partieRepository.findAll().stream()
            .filter(p -> p.getMj() != null && username.equals(p.getMj().getPseudo()))
            .toList();
    }
} 