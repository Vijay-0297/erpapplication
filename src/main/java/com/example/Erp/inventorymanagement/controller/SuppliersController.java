package com.example.Erp.inventorymanagement.controller;


import com.example.Erp.inventorymanagement.dto.SuppliersRequest;
import com.example.Erp.inventorymanagement.dto.SuppliersResponse;
import com.example.Erp.inventorymanagement.service.SuppliersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SuppliersController {

    private final SuppliersService supplierService;

    @PostMapping
    public ResponseEntity<SuppliersResponse> create(@RequestBody SuppliersRequest request) {
        return new ResponseEntity<>(supplierService.createSupplier(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuppliersResponse> get(@PathVariable Integer id) {
        return ResponseEntity.ok(supplierService.getSupplier(id));
    }

    @GetMapping
    public ResponseEntity<List<SuppliersResponse>> getAll() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuppliersResponse> update(
            @PathVariable Integer id,
            @RequestBody SuppliersRequest request) {

        return ResponseEntity.ok(supplierService.updateSupplier(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {

        supplierService.deleteSupplier(id);

        return ResponseEntity.ok("Supplier deleted successfully");
    }
}