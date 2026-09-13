package com.example.Erp.inventorymanagement.controller;

import com.example.Erp.inventorymanagement.dto.SalesReturnRequest;
import com.example.Erp.inventorymanagement.dto.SalesReturnResponse;
import com.example.Erp.inventorymanagement.service.SalesReturnService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales-returns")
@RequiredArgsConstructor
public class SalesReturnController {

    private final SalesReturnService salesReturnService;

    @PostMapping
    public ResponseEntity<SalesReturnResponse> create(
            @Valid @RequestBody SalesReturnRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(salesReturnService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<SalesReturnResponse>> getAll() {

        return ResponseEntity.ok(
                salesReturnService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesReturnResponse> getById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                salesReturnService.getById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalesReturnResponse> update(
            @PathVariable Integer id,
            @Valid @RequestBody SalesReturnRequest request) {

        return ResponseEntity.ok(
                salesReturnService.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id) {

        salesReturnService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<SalesReturnResponse>> getByCustomer(
            @PathVariable Long customerId) {

        return ResponseEntity.ok(
                salesReturnService.getByCustomerId(customerId)
        );
    }

    @GetMapping("/sale/{saleId}")
    public ResponseEntity<List<SalesReturnResponse>> getBySale(
            @PathVariable Integer saleId) {

        return ResponseEntity.ok(
                salesReturnService.getBySaleId(saleId)
        );
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<SalesReturnResponse>> getByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                salesReturnService.getByRefundStatus(status)
        );
    }
}