package com.tonilr.FinancialTracker.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Deshabilitar la autenticación para todas las rutas
        http.csrf().disable()
            .authorizeRequests()
            .anyRequest().permitAll();
        return http.build();
    }
}
