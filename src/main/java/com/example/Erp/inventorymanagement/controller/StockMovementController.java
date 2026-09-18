package com.example.Erp.inventorymanagement.controller;

import com.example.Erp.inventorymanagement.dto.StockMovementRequest;
import com.example.Erp.inventorymanagement.dto.StockMovementResponse;
import com.example.Erp.inventorymanagement.service.StockMovementService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-movements")
@RequiredArgsConstructor
public class StockMovementController {

    private final StockMovementService stockMovementService;


    // CREATE
    @PostMapping
    public ResponseEntity<StockMovementResponse> create(
            @Valid @RequestBody StockMovementRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        stockMovementService.create(request)
                );
    }


    // GET ALL
    @GetMapping
    public ResponseEntity<List<StockMovementResponse>>
    getAll() {

        return ResponseEntity.ok(
                stockMovementService.getAll()
        );
    }


    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<StockMovementResponse>
    getById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                stockMovementService.getById(id)
        );
    }


    // GET BY PRODUCT
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<StockMovementResponse>>
    getByProductId(
            @PathVariable Integer productId) {

        return ResponseEntity.ok(
                stockMovementService
                        .getByProductId(productId)
        );
    }


    // GET BY MOVEMENT TYPE
    @GetMapping("/type/{movementType}")
    public ResponseEntity<List<StockMovementResponse>>
    getByMovementType(
            @PathVariable String movementType) {

        return ResponseEntity.ok(
                stockMovementService
                        .getByMovementType(movementType)
        );
    }


    // GET BY REFERENCE ID
    @GetMapping("/reference/{referenceId}")
    public ResponseEntity<List<StockMovementResponse>>
    getByReferenceId(
            @PathVariable Integer referenceId) {

        return ResponseEntity.ok(
                stockMovementService
                        .getByReferenceId(referenceId)
        );
    }


    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<StockMovementResponse>
    update(
            @PathVariable Integer id,
            @Valid @RequestBody StockMovementRequest request) {

        return ResponseEntity.ok(
                stockMovementService.update(
                        id,
                        request
                )
        );
    }


    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id) {

        stockMovementService.delete(id);

        return ResponseEntity.noContent().build();
    }
}