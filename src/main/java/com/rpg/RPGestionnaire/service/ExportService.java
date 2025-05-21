package com.rpg.RPGestionnaire.service;

import com.rpg.RPGestionnaire.entity.Export;
import com.rpg.RPGestionnaire.repository.ExportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ExportService {
    @Autowired
    private ExportRepository exportRepository;

    public List<Export> findAll() {
        return exportRepository.findAll();
    }

    public Optional<Export> findById(UUID id) {
        return exportRepository.findById(id);
    }

    public Export save(Export export) {
        return exportRepository.save(export);
    }

    public void deleteById(UUID id) {
        exportRepository.deleteById(id);
    }
} 