package com.example.Erp.inventorymanagement.config;

import com.example.Erp.inventorymanagement.model.Role;
import com.example.Erp.inventorymanagement.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Seeds default roles on startup so POST /api/users has something to
 * point roleId at without needing a manual chicken-and-egg role setup
 * step first. Safe to run every restart - only inserts if missing.
 */
@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {

        seedRole("ADMIN", "Full system access");
        seedRole("USER", "Standard application user");
    }

    private void seedRole(String name, String description) {

        if (!roleRepository.existsByRoleName(name)) {

            Role role = Role.builder()
                    .roleName(name)
                    .description(description)
                    .build();

            roleRepository.save(role);
        }
    }
}
