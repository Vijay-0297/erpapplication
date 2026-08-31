package com.example.Erp.inventorymanagement.controller;

import com.example.Erp.inventorymanagement.dto.SalesRequest;
import com.example.Erp.inventorymanagement.dto.SalesResponse;
import com.example.Erp.inventorymanagement.service.SalesService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

    @PostMapping
    public ResponseEntity<SalesResponse> createSale(
            @RequestBody SalesRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(salesService.createSale(request));
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<SalesResponse> getSaleById(
            @PathVariable Integer saleId) {

        return ResponseEntity.ok(
                salesService.getSaleById(saleId)
        );
    }

    @GetMapping
    public ResponseEntity<List<SalesResponse>> getAllSales() {

        return ResponseEntity.ok(
                salesService.getAllSales()
        );
    }

    @PutMapping("/{saleId}")
    public ResponseEntity<SalesResponse> updateSale(
            @PathVariable Integer saleId,
            @RequestBody SalesRequest request) {

        return ResponseEntity.ok(
                salesService.updateSale(saleId, request)
        );
    }

    @DeleteMapping("/{saleId}")
    public ResponseEntity<String> deleteSale(
            @PathVariable Integer saleId) {

        salesService.deleteSale(saleId);

        return ResponseEntity.ok(
                "Sale deleted successfully"
        );
    }
}