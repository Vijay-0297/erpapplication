package com.example.Erp.inventorymanagement.controller;

import com.example.Erp.inventorymanagement.dto.SalesItemRequest;
import com.example.Erp.inventorymanagement.dto.SalesItemResponse;
import com.example.Erp.inventorymanagement.service.SalesItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales-items")
@RequiredArgsConstructor
public class SalesItemController {

    private final SalesItemService salesItemService;

    @PostMapping
    public ResponseEntity<SalesItemResponse> create(
            @Valid @RequestBody SalesItemRequest request
    ) {

        SalesItemResponse response =
                salesItemService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<SalesItemResponse>> getAll() {

        return ResponseEntity.ok(
                salesItemService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesItemResponse> getById(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                salesItemService.getById(id)
        );
    }

    @GetMapping("/sale/{saleId}")
    public ResponseEntity<List<SalesItemResponse>> getBySaleId(
            @PathVariable Integer saleId
    ) {

        return ResponseEntity.ok(
                salesItemService.getBySaleId(saleId)
        );
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<SalesItemResponse>> getByProductId(
            @PathVariable Integer productId
    ) {

        return ResponseEntity.ok(
                salesItemService.getByProductId(productId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalesItemResponse> update(
            @PathVariable Integer id,
            @Valid @RequestBody SalesItemRequest request
    ) {

        return ResponseEntity.ok(
                salesItemService.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id
    ) {

        salesItemService.delete(id);

        return ResponseEntity.noContent().build();
    }
}