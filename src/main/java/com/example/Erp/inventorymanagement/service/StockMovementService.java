package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.StockMovementRequest;
import com.example.Erp.inventorymanagement.dto.StockMovementResponse;

import java.util.List;

public interface StockMovementService {

    StockMovementResponse create(
            StockMovementRequest request
    );

    StockMovementResponse getById(
            Integer id
    );

    List<StockMovementResponse> getAll();

    List<StockMovementResponse> getByProductId(
            Integer productId
    );

    List<StockMovementResponse> getByMovementType(
            String movementType
    );

    List<StockMovementResponse> getByReferenceId(
            Integer referenceId
    );

    StockMovementResponse update(
            Integer id,
            StockMovementRequest request
    );

    void delete(Integer id);
}