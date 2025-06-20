package com.rpg.RPGestionnaire.service;

import com.rpg.RPGestionnaire.entity.Utilisateur;
import com.rpg.RPGestionnaire.repository.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UtilisateurService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Tentative de connexion avec : " + username);
        Optional<Utilisateur> byPseudo = utilisateurRepository.findByPseudo(username);
        Optional<Utilisateur> byEmail = utilisateurRepository.findByEmail(username);
        System.out.println("Recherche par pseudo : " + byPseudo.map(Utilisateur::getPseudo).orElse("non trouvé"));
        System.out.println("Recherche par email : " + byEmail.map(Utilisateur::getEmail).orElse("non trouvé"));
        Utilisateur utilisateur = byPseudo.or(() -> byEmail)
            .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé: " + username));
        System.out.println("Utilisateur trouvé : pseudo=" + utilisateur.getPseudo() + ", hash=" + utilisateur.getPasswordHash());
        return new User(
            utilisateur.getPseudo(),
            utilisateur.getPasswordHash(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole().name().toUpperCase()))
        );
    }

    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> findById(UUID id) {
        return utilisateurRepository.findById(id);
    }

    public Utilisateur save(Utilisateur utilisateur) {
        if (utilisateur.getPasswordHash() != null && !utilisateur.getPasswordHash().startsWith("$2a$")) {
            utilisateur.setPasswordHash(passwordEncoder.encode(utilisateur.getPasswordHash()));
        }
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteById(UUID id) {
        utilisateurRepository.deleteById(id);
    }

    public boolean existsByPseudo(String pseudo) {
        return utilisateurRepository.findByPseudo(pseudo).isPresent();
    }

    public boolean existsByEmail(String email) {
        return utilisateurRepository.findByEmail(email).isPresent();
    }

    public Optional<Utilisateur> findByPseudo(String pseudo) {
        return utilisateurRepository.findByPseudo(pseudo);
    }

    public boolean verifyPassword(Utilisateur utilisateur, String rawPassword) {
        return passwordEncoder.matches(rawPassword, utilisateur.getPasswordHash());
    }
} 