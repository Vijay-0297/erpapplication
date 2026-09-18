package com.example.Erp.inventorymanagement.controller;

import com.example.Erp.inventorymanagement.dto.SalesReturnItemRequest;
import com.example.Erp.inventorymanagement.dto.SalesReturnItemResponse;
import com.example.Erp.inventorymanagement.service.SalesReturnItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales-return-items")
@RequiredArgsConstructor
public class SalesReturnItemController {

    private final SalesReturnItemService salesReturnItemService;

    @PostMapping
    public ResponseEntity<SalesReturnItemResponse> create(
            @Valid @RequestBody SalesReturnItemRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(salesReturnItemService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesReturnItemResponse> getById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                salesReturnItemService.getById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<SalesReturnItemResponse>> getAll() {

        return ResponseEntity.ok(
                salesReturnItemService.getAll()
        );
    }

    @GetMapping("/return/{salesReturnId}")
    public ResponseEntity<List<SalesReturnItemResponse>>
    getBySalesReturnId(
            @PathVariable Integer salesReturnId) {

        return ResponseEntity.ok(
                salesReturnItemService
                        .getBySalesReturnId(salesReturnId)
        );
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<SalesReturnItemResponse>>
    getByProductId(
            @PathVariable Integer productId) {

        return ResponseEntity.ok(
                salesReturnItemService
                        .getByProductId(productId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalesReturnItemResponse> update(
            @PathVariable Integer id,
            @Valid @RequestBody SalesReturnItemRequest request) {

        return ResponseEntity.ok(
                salesReturnItemService.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id) {

        salesReturnItemService.delete(id);

        return ResponseEntity.noContent().build();
    }
}