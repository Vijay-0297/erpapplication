package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.AuthResponse;
import com.example.Erp.inventorymanagement.dto.LoginRequest;
import com.example.Erp.inventorymanagement.dto.RegisterRequest;
import com.example.Erp.inventorymanagement.dto.RegisterResponse;
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

    public RegisterResponse register(RegisterRequest request) {

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

        // Fetch role by roleId if provided, else fallback to default USER role
        Role role;
        if (request.getRoleId() != null) {
            role = roleRepository.findById(request.getRoleId().longValue())
                    .orElseThrow(() -> new RuntimeException("Role not found with ID: " + request.getRoleId()));
        } else {
            role = roleRepository.findByRoleName("USER")
                    .orElseThrow(() -> new RuntimeException(
                            "Default USER role missing – restart the app so DataSeeder can create it"));
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .mobile(request.getMobile())
                .role(role)
                .status("active")
                .build();

        User savedUser = userRepository.save(user);

        return RegisterResponse.builder()
                .userId(savedUser.getUserId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .fullName(savedUser.getFullName())
                .mobile(savedUser.getMobile())
                .roleId(savedUser.getRole().getRoleId().intValue())
                .role(savedUser.getRole().getRoleName())
                .status(savedUser.getStatus())
                .message("User registered successfully")
                .build();
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
