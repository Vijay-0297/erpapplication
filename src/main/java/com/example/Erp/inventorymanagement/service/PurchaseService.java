package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.PurchaseRequest;
import com.example.Erp.inventorymanagement.dto.PurchaseResponse;

import java.util.List;

public interface PurchaseService {

    PurchaseResponse create(PurchaseRequest request);

    PurchaseResponse update(Integer id, PurchaseRequest request);

    PurchaseResponse getById(Integer id);

    List<PurchaseResponse> getAll();

    void delete(Integer id);
}
