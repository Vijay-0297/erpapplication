package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.PurchaseItemRequest;
import com.example.Erp.inventorymanagement.dto.PurchaseItemResponse;

import java.util.List;

public interface PurchaseItemService {

    PurchaseItemResponse create(PurchaseItemRequest request);

    PurchaseItemResponse getById(Integer id);

    List<PurchaseItemResponse> getAll();

    List<PurchaseItemResponse> getByPurchaseId(Integer purchaseId);

    List<PurchaseItemResponse> getByProductId(Integer productId);

    PurchaseItemResponse update(Integer id, PurchaseItemRequest request);

    void delete(Integer id);
}