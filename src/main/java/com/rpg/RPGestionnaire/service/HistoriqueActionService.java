package com.rpg.RPGestionnaire.service;

import com.rpg.RPGestionnaire.entity.HistoriqueAction;
import com.rpg.RPGestionnaire.repository.HistoriqueActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class HistoriqueActionService {
    @Autowired
    private HistoriqueActionRepository historiqueActionRepository;

    public List<HistoriqueAction> findAll() {
        return historiqueActionRepository.findAll();
    }

    public void save(HistoriqueAction action) {
        historiqueActionRepository.save(action);
    }

    public void deleteById(UUID id) {
        historiqueActionRepository.deleteById(id);
    }
} 