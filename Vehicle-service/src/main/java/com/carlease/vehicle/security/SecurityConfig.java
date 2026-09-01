package com.carlease.vehicle.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter) {

        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .httpBasic(httpBasic -> httpBasic.disable())

                .formLogin(formLogin -> formLogin.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth

                        // Public health endpoint
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/vehicles/health"
                        ).permitAll()

                        // Read operations
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/vehicles/**"
                        ).hasAnyRole(
                                "CUSTOMER",
                                "SALES_EXECUTIVE",
                                "ADMIN",
                                "CORPORATE_USER"
                        )

                        // Create vehicle
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/vehicles"
                        ).hasAnyRole(
                                "SALES_EXECUTIVE",
                                "ADMIN"
                        )

                        // Update vehicle
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/vehicles/**"
                        ).hasAnyRole(
                                "SALES_EXECUTIVE",
                                "ADMIN"
                        )

                        // Update vehicle status
                        .requestMatchers(
                                HttpMethod.PATCH,
                                "/api/vehicles/**"
                        ).hasAnyRole(
                                "SALES_EXECUTIVE",
                                "ADMIN"
                        )

                        // Delete vehicle
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/vehicles/**"
                        ).hasRole("ADMIN")

                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}
