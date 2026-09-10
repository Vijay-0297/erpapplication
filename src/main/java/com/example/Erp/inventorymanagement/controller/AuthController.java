package com.example.Erp.inventorymanagement.controller;

import com.example.Erp.inventorymanagement.dto.AuthResponse;
import com.example.Erp.inventorymanagement.dto.LoginRequest;
import com.example.Erp.inventorymanagement.dto.RegisterRequest;
import com.example.Erp.inventorymanagement.dto.RegisterResponse;
import com.example.Erp.inventorymanagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @RequestBody RegisterRequest request
    ) {

        RegisterResponse response =
                authenticationService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request
    ) {

        AuthResponse response =
                authenticationService.login(request);

        return ResponseEntity.ok(response);
    }
}