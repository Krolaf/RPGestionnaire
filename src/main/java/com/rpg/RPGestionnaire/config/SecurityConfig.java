package com.rpg.RPGestionnaire.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/register", "/login", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/entites/admin/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/entites/gm/**").hasAuthority("ROLE_MJ")
                .requestMatchers("/parties/admin/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/parties/gm/**").hasAuthority("ROLE_MJ")
                .requestMatchers("/parties/player/**").hasAuthority("ROLE_JOUEUR")
                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/front/gm/**").hasAuthority("ROLE_MJ")
                .requestMatchers("/front/player/**").hasAuthority("ROLE_JOUEUR")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );
        return http.build();
    }
} 