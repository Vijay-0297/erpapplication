package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.AuthResponse;
import com.example.Erp.inventorymanagement.dto.LoginRequest;
import com.example.Erp.inventorymanagement.dto.RegisterRequest;
import com.example.Erp.inventorymanagement.model.User;
import com.example.Erp.inventorymanagement.model.Role;
import com.example.Erp.inventorymanagement.repository.RoleRepository;
import com.example.Erp.inventorymanagement.repository.UserRepository;
import com.example.Erp.inventorymanagement.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {

        if (isBlank(request.getUsername()) ||
                isBlank(request.getEmail()) ||
                isBlank(request.getPassword())) {

            throw new IllegalArgumentException(
                    "username, email and password are required"
            );
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Assign the default USER role so user.getRole() is never null
        Role defaultRole = roleRepository.findByRoleName("USER")
                .orElseThrow(() -> new RuntimeException(
                        "Default USER role missing – restart the app so DataSeeder can create it"));

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .role(defaultRole)
                .status("active")
                .build();

        User savedUser = userRepository.save(user);

        // Subject MUST be the email - CustomUserDetailsService looks users
        // up by email, so every other part of the auth flow keys off it too.
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User
                        .withUsername(savedUser.getEmail())
                        .password(savedUser.getPasswordHash())
                        .authorities("ROLE_USER")
                        .build();

        String token = jwtUtil.generateToken(userDetails);

        return new AuthResponse(token, "Bearer");
    }

    public AuthResponse login(LoginRequest request) {

        if (isBlank(request.getEmail()) || isBlank(request.getPassword())) {

            // Fail fast with a clear message instead of letting a null
            // email fall through to the authentication manager, which
            // would surface as a confusing 401 "bad credentials".
            throw new IllegalArgumentException(
                    "email and password are required"
            );
        }

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        String token = jwtUtil.generateToken(userDetails);

        return new AuthResponse(token, "Bearer");
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
