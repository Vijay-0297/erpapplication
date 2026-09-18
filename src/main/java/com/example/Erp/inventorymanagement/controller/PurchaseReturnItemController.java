package com.example.Erp.inventorymanagement.controller;

import com.example.Erp.inventorymanagement.dto.PurchaseReturnItemRequest;
import com.example.Erp.inventorymanagement.dto.PurchaseReturnItemResponse;
import com.example.Erp.inventorymanagement.service.PurchaseReturnItemService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-return-items")
@RequiredArgsConstructor
public class PurchaseReturnItemController {

    private final PurchaseReturnItemService service;


    @PostMapping
    public ResponseEntity<PurchaseReturnItemResponse> create(
            @Valid @RequestBody PurchaseReturnItemRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }


    @GetMapping
    public ResponseEntity<List<PurchaseReturnItemResponse>> getAll() {

        return ResponseEntity.ok(
                service.getAll()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<PurchaseReturnItemResponse> getById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                service.getById(id)
        );
    }


    @GetMapping("/purchase-return/{purchaseReturnId}")
    public ResponseEntity<List<PurchaseReturnItemResponse>>
    getByPurchaseReturnId(
            @PathVariable Integer purchaseReturnId) {

        return ResponseEntity.ok(
                service.getByPurchaseReturnId(
                        purchaseReturnId
                )
        );
    }


    @GetMapping("/product/{productId}")
    public ResponseEntity<List<PurchaseReturnItemResponse>>
    getByProductId(
            @PathVariable Integer productId) {

        return ResponseEntity.ok(
                service.getByProductId(productId)
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<PurchaseReturnItemResponse> update(
            @PathVariable Integer id,
            @Valid @RequestBody PurchaseReturnItemRequest request) {

        return ResponseEntity.ok(
                service.update(id, request)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}