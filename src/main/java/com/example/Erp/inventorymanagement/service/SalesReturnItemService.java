package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.SalesReturnItemRequest;
import com.example.Erp.inventorymanagement.dto.SalesReturnItemResponse;

import java.util.List;

public interface SalesReturnItemService {

    SalesReturnItemResponse create(SalesReturnItemRequest request);

    SalesReturnItemResponse getById(Integer id);

    List<SalesReturnItemResponse> getAll();

    List<SalesReturnItemResponse> getBySalesReturnId(Integer salesReturnId);

    List<SalesReturnItemResponse> getByProductId(Integer productId);

    SalesReturnItemResponse update(
            Integer id,
            SalesReturnItemRequest request
    );

    void delete(Integer id);
}