
        package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.SalesReturnRequest;
import com.example.Erp.inventorymanagement.dto.SalesReturnResponse;

import java.util.List;

public interface SalesReturnService {

    SalesReturnResponse create(SalesReturnRequest request);

    List<SalesReturnResponse> getAll();

    SalesReturnResponse getById(Integer id);

    SalesReturnResponse update(
            Integer id,
            SalesReturnRequest request
    );

    void delete(Integer id);

    List<SalesReturnResponse> getBySaleId(Integer saleId);

    List<SalesReturnResponse> getByCustomerId(Integer customerId);

    List<SalesReturnResponse> getByRefundStatus(String refundStatus);
}
