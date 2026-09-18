package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.PurchaseReturnItemRequest;
import com.example.Erp.inventorymanagement.dto.PurchaseReturnItemResponse;

import java.util.List;

public interface PurchaseReturnItemService {

    PurchaseReturnItemResponse create(
            PurchaseReturnItemRequest request
    );

    PurchaseReturnItemResponse getById(
            Integer id
    );

    List<PurchaseReturnItemResponse> getAll();

    List<PurchaseReturnItemResponse> getByPurchaseReturnId(
            Integer purchaseReturnId
    );

    List<PurchaseReturnItemResponse> getByProductId(
            Integer productId
    );

    PurchaseReturnItemResponse update(
            Integer id,
            PurchaseReturnItemRequest request
    );

    void delete(Integer id);
}