package com.example.Erp.inventorymanagement.controller;

import com.example.Erp.inventorymanagement.dto.PurchaseRequest;
import com.example.Erp.inventorymanagement.dto.PurchaseResponse;
import com.example.Erp.inventorymanagement.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseResponse create(@RequestBody PurchaseRequest request) {
        return purchaseService.create(request);
    }

    @GetMapping
    public List<PurchaseResponse> getAll() {
        return purchaseService.getAll();
    }

    @GetMapping("/{id}")
    public PurchaseResponse getById(@PathVariable Integer id) {
        return purchaseService.getById(id);
    }

    @PutMapping("/{id}")
    public PurchaseResponse update(@PathVariable Integer id,
                                   @RequestBody PurchaseRequest request) {
        return purchaseService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        purchaseService.delete(id);
    }
}
