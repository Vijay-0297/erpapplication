package com.example.Erp.inventorymanagement.controller;

import com.example.Erp.inventorymanagement.dto.PurchaseReturnRequest;
import com.example.Erp.inventorymanagement.dto.PurchaseReturnResponse;
import com.example.Erp.inventorymanagement.service.PurchaseReturnService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-returns")
@RequiredArgsConstructor
public class PurchaseReturnController {

    private final PurchaseReturnService purchaseReturnService;

    @PostMapping
    public ResponseEntity<PurchaseReturnResponse> create(
            @Valid @RequestBody PurchaseReturnRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(purchaseReturnService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseReturnResponse> getById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                purchaseReturnService.getById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<PurchaseReturnResponse>> getAll() {

        return ResponseEntity.ok(
                purchaseReturnService.getAll()
        );
    }

    @GetMapping("/purchase/{purchaseId}")
    public ResponseEntity<List<PurchaseReturnResponse>>
    getByPurchaseId(
            @PathVariable Integer purchaseId) {

        return ResponseEntity.ok(
                purchaseReturnService
                        .getByPurchaseId(purchaseId)
        );
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<PurchaseReturnResponse>>
    getBySupplierId(
            @PathVariable Integer supplierId) {

        return ResponseEntity.ok(
                purchaseReturnService
                        .getBySupplierId(supplierId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseReturnResponse> update(
            @PathVariable Integer id,
            @Valid @RequestBody PurchaseReturnRequest request) {

        return ResponseEntity.ok(
                purchaseReturnService.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id) {

        purchaseReturnService.delete(id);

        return ResponseEntity.noContent().build();
    }
}