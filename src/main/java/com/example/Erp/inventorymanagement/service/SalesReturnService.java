package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.SalesReturnRequest;
import com.example.Erp.inventorymanagement.dto.SalesReturnResponse;

import java.util.List;

public interface SalesReturnService {

    SalesReturnResponse create(SalesReturnRequest request);

    SalesReturnResponse getById(Integer id);

    List<SalesReturnResponse> getAll();

    SalesReturnResponse update(Integer id, SalesReturnRequest request);

    void delete(Integer id);

    List<SalesReturnResponse> getByCustomerId(Long customerId);

    List<SalesReturnResponse> getBySaleId(Integer saleId);

    List<SalesReturnResponse> getByRefundStatus(String refundStatus);
}