package com.proyecto.citiodeportivo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());

        // ✅ CORS Configuration
        http.cors(cors -> cors.configurationSource(request -> {
            var config = new CorsConfiguration();
            config.setAllowedOrigins(List.of("http://localhost:4200"));
            config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            config.setAllowedHeaders(List.of("*"));
            config.setAllowCredentials(true);
            config.setMaxAge(3600L);
            return config;
        }));

        // ✅ Session Management
        http.sessionManagement(s ->
                s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        // ✅ Authorization - ACTUALIZADO
        http.authorizeHttpRequests(auth -> auth

                // Públicos
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/auth/registro").permitAll()
                .requestMatchers("/auth/usuario/*/disponible").permitAll()
                .requestMatchers("/auth/email/*/disponible").permitAll()

                // CLIENTE - perfil propio
                .requestMatchers(HttpMethod.GET, "/clientes/me").hasRole("CLIENTE")
                .requestMatchers(HttpMethod.PUT, "/clientes/me").hasRole("CLIENTE")

                // ADMIN gestiona clientes
                .requestMatchers("/clientes/**").hasRole("ADMIN")

                // ENTRENADOR
                .requestMatchers("/entrenadores/**").hasAnyRole("ENTRENADOR", "ADMIN")
                .requestMatchers("/disponibilidad/**").hasAnyRole("ENTRENADOR", "ADMIN")

                // ADMIN
                .requestMatchers("/administradores/**").hasRole("ADMIN")

                .anyRequest().authenticated()
        );


        // ✅ JWT Filter
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // 🔥 Desactivar usuario por defecto
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> null;
    }

    // 🔥 Evita que Spring cree AuthenticationManager por defecto
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }
}