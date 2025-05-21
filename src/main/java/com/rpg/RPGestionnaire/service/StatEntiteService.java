package com.rpg.RPGestionnaire.service;

import com.rpg.RPGestionnaire.entity.StatEntite;
import com.rpg.RPGestionnaire.repository.StatEntiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StatEntiteService {
    @Autowired
    private StatEntiteRepository statEntiteRepository;

    public List<StatEntite> findAll() {
        return statEntiteRepository.findAll();
    }

    public Optional<StatEntite> findById(UUID id) {
        return statEntiteRepository.findById(id);
    }

    public StatEntite save(StatEntite statEntite) {
        return statEntiteRepository.save(statEntite);
    }

    public void deleteById(UUID id) {
        statEntiteRepository.deleteById(id);
    }
} 