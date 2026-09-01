package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.SalesRequest;
import com.example.Erp.inventorymanagement.dto.SalesResponse;

import java.util.List;

public interface SalesService {

    SalesResponse createSale(SalesRequest request);

    SalesResponse getSaleById(Integer saleId);

    List<SalesResponse> getAllSales();

    SalesResponse updateSale(Integer saleId, SalesRequest request);

    void deleteSale(Integer saleId);
}