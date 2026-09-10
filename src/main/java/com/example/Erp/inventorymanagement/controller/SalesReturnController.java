
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

    private final SalesReturnService service;

    @PostMapping
    public ResponseEntity<SalesReturnResponse> create(
            @Valid @RequestBody SalesReturnRequest request
    ) {

        return new ResponseEntity<>(
                service.create(request),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<SalesReturnResponse>> getAll() {

        return ResponseEntity.ok(
                service.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesReturnResponse> getById(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                service.getById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalesReturnResponse> update(
            @PathVariable Integer id,
            @Valid @RequestBody SalesReturnRequest request
    ) {

        return ResponseEntity.ok(
                service.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Integer id
    ) {

        service.delete(id);

        return ResponseEntity.ok(
                "Sales return deleted successfully."
        );
    }

    @GetMapping("/sale/{saleId}")
    public ResponseEntity<List<SalesReturnResponse>> getBySaleId(
            @PathVariable Integer saleId
    ) {

        return ResponseEntity.ok(
                service.getBySaleId(saleId)
        );
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<SalesReturnResponse>> getByCustomerId(
            @PathVariable Integer customerId
    ) {

        return ResponseEntity.ok(
                service.getByCustomerId(customerId)
        );
    }

    @GetMapping("/status/{refundStatus}")
    public ResponseEntity<List<SalesReturnResponse>> getByRefundStatus(
            @PathVariable String refundStatus
    ) {

        return ResponseEntity.ok(
                service.getByRefundStatus(refundStatus)
        );
    }
}

