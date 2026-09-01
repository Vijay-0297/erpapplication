package com.example.Erp.inventorymanagement.config;

import com.example.Erp.inventorymanagement.model.User;
import com.example.Erp.inventorymanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * IMPORTANT: The whole auth flow (AuthService, JwtUtil, JwtAuthenticationFilter)
 * uses the user's EMAIL as the JWT subject / login identifier.
 * So this lookup MUST match by email, not by the "username" column,
 * otherwise every token fails validation and every protected endpoint
 * returns 403 even with a valid Bearer token.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(usernameOrEmail)
                .or(() -> userRepository.findByUsername(usernameOrEmail))
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found with email or username: " + usernameOrEmail
                        )
                );

        String role = "USER";

        if (user.getRole() != null &&
                user.getRole().getRoleName() != null &&
                !user.getRole().getRoleName().isBlank()) {

            role = user.getRole().getRoleName().trim().toUpperCase();
        }

        // Make sure authority is ROLE_USER, ROLE_ADMIN, etc.
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPasswordHash())
                .authorities(new SimpleGrantedAuthority(role))
                .build();
    }
}
