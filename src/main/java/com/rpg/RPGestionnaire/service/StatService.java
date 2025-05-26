package com.rpg.RPGestionnaire.service;

import com.rpg.RPGestionnaire.entity.Stat;
import com.rpg.RPGestionnaire.repository.StatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StatService {
    @Autowired
    private StatRepository statRepository;

    public List<Stat> findAll() {
        return statRepository.findAll();
    }

    public Optional<Stat> findById(UUID id) {
        return statRepository.findById(id);
    }

    public Stat save(Stat stat) {
        return statRepository.save(stat);
    }

    public void deleteById(UUID id) {
        statRepository.deleteById(id);
    }

    public List<Stat> findByFichePersonnageId(UUID ficheId) {
        return statRepository.findByFichePersonnageId(ficheId);
    }

    public List<Stat> findByEntiteId(UUID entiteId) {
        return statRepository.findByEntiteId(entiteId);
    }
} 