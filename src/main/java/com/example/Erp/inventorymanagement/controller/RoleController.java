package com.example.Erp.inventorymanagement.controller;


import com.example.Erp.inventorymanagement.dto.RoleRequest;
import com.example.Erp.inventorymanagement.dto.RoleResponse;
import com.example.Erp.inventorymanagement.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleResponse> createRole(
            @Valid @RequestBody RoleRequest request
    ) {

        RoleResponse response = roleService.createRole(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<RoleResponse> getRoleById(
            @PathVariable Long roleId
    ) {

        return ResponseEntity.ok(
                roleService.getRoleById(roleId)
        );
    }

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAllRoles() {

        return ResponseEntity.ok(
                roleService.getAllRoles()
        );
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<RoleResponse> updateRole(
            @PathVariable Long roleId,
            @Valid @RequestBody RoleRequest request
    ) {

        return ResponseEntity.ok(
                roleService.updateRole(roleId, request)
        );
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteRole(
            @PathVariable Long roleId
    ) {

        roleService.deleteRole(roleId);

        return ResponseEntity.noContent().build();
    }
}