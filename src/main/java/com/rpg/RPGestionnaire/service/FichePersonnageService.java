package com.rpg.RPGestionnaire.service;

import com.rpg.RPGestionnaire.entity.FichePersonnage;
import com.rpg.RPGestionnaire.repository.FichePersonnageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class FichePersonnageService {
    @Autowired
    private FichePersonnageRepository fichePersonnageRepository;

    public List<FichePersonnage> findAll() {
        return fichePersonnageRepository.findAll();
    }

    public Optional<FichePersonnage> findById(UUID id) {
        return fichePersonnageRepository.findById(id);
    }

    public FichePersonnage save(FichePersonnage fichePersonnage) {
        return fichePersonnageRepository.save(fichePersonnage);
    }

    public void deleteById(UUID id) {
        fichePersonnageRepository.deleteById(id);
    }

    public List<FichePersonnage> findByUtilisateurPseudo(String pseudo) {
        return fichePersonnageRepository.findByUtilisateur_Pseudo(pseudo);
    }

    public List<FichePersonnage> findByPartieId(UUID partieId) {
        return fichePersonnageRepository.findByPartie_Id(partieId);
    }
} 