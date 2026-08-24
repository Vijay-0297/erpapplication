package com.example.Erp.inventorymanagement.config;

import com.example.Erp.inventorymanagement.model.User;
import com.example.Erp.inventorymanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found: " + username
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
                .withUsername(user.getUsername())
                .password(user.getPasswordHash())
                .authorities(new SimpleGrantedAuthority(role))
                .build();
    }
}