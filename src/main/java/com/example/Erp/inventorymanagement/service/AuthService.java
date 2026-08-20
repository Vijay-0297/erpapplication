package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.AuthResponse;
import com.example.Erp.inventorymanagement.dto.LoginRequest;
import com.example.Erp.inventorymanagement.dto.RegisterRequest;
import com.example.Erp.inventorymanagement.model.User;
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
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthResponse register(
            RegisterRequest request) {

        if (userRepository.existsByUsername(
                request.getUsername())) {

            throw new RuntimeException(
                    "Username already exists"
            );
        }

        if (userRepository.existsByEmail(
                request.getEmail())) {

            throw new RuntimeException(
                    "Email already exists"
            );
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .passwordHash(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )
                .fullName(request.getFullName())
                .status("active")
                .build();

        User savedUser =
                userRepository.save(user);

        UserDetails userDetails =
                org.springframework.security.core.userdetails.User
                        .withUsername(savedUser.getEmail())
                        .password(savedUser.getPasswordHash())
                        .authorities("ROLE_USER")
                        .build();

        String token =
                jwtUtil.generateToken(userDetails);

        return new AuthResponse(
                token,
                "Bearer"
        );
    }

    public AuthResponse login(
            LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        String token =
                jwtUtil.generateToken(userDetails);

        return new AuthResponse(
                token,
                "Bearer"
        );
    }
}