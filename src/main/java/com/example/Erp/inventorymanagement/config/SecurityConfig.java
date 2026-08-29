package com.example.Erp.inventorymanagement.config;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailsService userDetailsService;

    // =========================
    // PASSWORD ENCODER
    // =========================

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // =========================
    // AUTHENTICATION PROVIDER
    // =========================

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    // =========================
    // AUTHENTICATION MANAGER
    // =========================

    @Bean
    public AuthenticationManager authenticationManager() {

        return new ProviderManager(
                authenticationProvider()
        );
    }

    // =========================
    // MODELMAPPER
    // =========================

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    // =========================
    // CORS
    // (needed so Postman preflight / a future frontend isn't blocked)
    // =========================

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // =========================
    // CLEAR ERROR RESPONSES
    // 401 = no/invalid token, 403 = valid token but wrong role.
    // Without these, Spring silently returns an EMPTY 403 body for both
    // cases, which is why it looked like everything was "403 forbidden".
    // =========================

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, ex) -> {
            response.setStatus(403);
            response.setContentType("application/json");
            response.getWriter().write(
                    "{\"status\":403,\"error\":\"Forbidden\",\"message\":\"You do not have permission to access this resource.\"}"
            );
        };
    }

    @Bean
    public org.springframework.security.web.AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, ex) -> {
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().write(
                    "{\"status\":401,\"error\":\"Unauthorized\",\"message\":\"Missing or invalid Bearer token. Login via /api/auth/login first.\"}"
            );
        };
    }

    // =========================
    // SECURITY FILTER CHAIN
    // =========================

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http

                // Disable CSRF because this is a JWT REST API
                .csrf(csrf -> csrf.disable())

                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // JWT does not use HTTP sessions
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(authenticationEntryPoint())
                        .accessDeniedHandler(accessDeniedHandler())
                )

                // Authorization rules
                .authorizeHttpRequests(auth -> auth

                        // -------------------------
                        // PUBLIC ENDPOINTS
                        // -------------------------

                        .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()

                        .requestMatchers(
                                "/api/auth/login",
                                "/api/auth/register"
                        ).permitAll()

                        // -------------------------
                        // EVERYTHING ELSE REQUIRES A VALID BEARER TOKEN
                        // -------------------------

                        .requestMatchers("/api/users/**").authenticated()
                        .requestMatchers("/api/categories/**").authenticated()
                        .requestMatchers("/api/products/**").authenticated()
                        .requestMatchers("/api/inventory/**").authenticated()
                        .requestMatchers("/api/suppliers/**").authenticated()
                        .requestMatchers("/api/customers/**").authenticated()
                        .requestMatchers("/api/purchases/**").authenticated()
                        .requestMatchers("/api/sales/**").authenticated()

                        .anyRequest().authenticated()
                )

                // JWT filter
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}
