package com.rpg.RPGestionnaire.service;

import com.rpg.RPGestionnaire.entity.Entite;
import com.rpg.RPGestionnaire.repository.EntiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EntiteService {
    @Autowired
    private EntiteRepository entiteRepository;

    public List<Entite> findAll() {
        return entiteRepository.findAll();
    }

    public Optional<Entite> findById(UUID id) {
        return entiteRepository.findById(id);
    }

    public Entite save(Entite entite) {
        return entiteRepository.save(entite);
    }

    public void deleteById(UUID id) {
        entiteRepository.deleteById(id);
    }

    public List<Entite> findByGmUsername(String username) {
        // À adapter selon la structure de l'entité (ex: champ createur ou mj)
        return entiteRepository.findAll(); // TODO: filtrer par MJ si la structure le permet
    }
} 