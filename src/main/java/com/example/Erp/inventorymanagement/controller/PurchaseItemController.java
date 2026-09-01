package com.example.Erp.inventorymanagement.controller;

import com.example.Erp.inventorymanagement.dto.PurchaseItemRequest;
import com.example.Erp.inventorymanagement.dto.PurchaseItemResponse;
import com.example.Erp.inventorymanagement.service.PurchaseItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-items")
@RequiredArgsConstructor
public class PurchaseItemController {

    private final PurchaseItemService purchaseItemService;

    // CREATE
    @PostMapping
    public ResponseEntity<PurchaseItemResponse> create(
            @RequestBody PurchaseItemRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(purchaseItemService.create(request));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<PurchaseItemResponse>> getAll() {

        return ResponseEntity.ok(
                purchaseItemService.getAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseItemResponse> getById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                purchaseItemService.getById(id));
    }

    // GET BY PURCHASE ID
    @GetMapping("/purchase/{purchaseId}")
    public ResponseEntity<List<PurchaseItemResponse>> getByPurchaseId(
            @PathVariable Integer purchaseId) {

        return ResponseEntity.ok(
                purchaseItemService
                        .getByPurchaseId(purchaseId));
    }

    // GET BY PRODUCT ID
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<PurchaseItemResponse>> getByProductId(
            @PathVariable Integer productId) {

        return ResponseEntity.ok(
                purchaseItemService
                        .getByProductId(productId));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<PurchaseItemResponse> update(
            @PathVariable Integer id,
            @RequestBody PurchaseItemRequest request) {

        return ResponseEntity.ok(
                purchaseItemService.update(id, request));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id) {

        purchaseItemService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
