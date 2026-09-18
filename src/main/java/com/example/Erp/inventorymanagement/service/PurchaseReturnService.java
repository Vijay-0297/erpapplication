package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.PurchaseReturnRequest;
import com.example.Erp.inventorymanagement.dto.PurchaseReturnResponse;

import java.util.List;

public interface PurchaseReturnService {

    PurchaseReturnResponse create(
            PurchaseReturnRequest request
    );

    PurchaseReturnResponse getById(
            Integer id
    );

    List<PurchaseReturnResponse> getAll();

    List<PurchaseReturnResponse> getByPurchaseId(
            Integer purchaseId
    );

    List<PurchaseReturnResponse> getBySupplierId(
            Integer supplierId
    );

    PurchaseReturnResponse update(
            Integer id,
            PurchaseReturnRequest request
    );

    void delete(Integer id);
}