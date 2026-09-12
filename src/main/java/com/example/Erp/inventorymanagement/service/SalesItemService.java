package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.SalesItemRequest;
import com.example.Erp.inventorymanagement.dto.SalesItemResponse;

import java.util.List;

public interface SalesItemService {

    SalesItemResponse create(SalesItemRequest request);

    SalesItemResponse getById(Integer id);

    List<SalesItemResponse> getAll();

    List<SalesItemResponse> getBySaleId(Integer saleId);

    List<SalesItemResponse> getByProductId(Integer productId);

    SalesItemResponse update(Integer id, SalesItemRequest request);

    void delete(Integer id);
}