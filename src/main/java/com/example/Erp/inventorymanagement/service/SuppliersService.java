package com.example.Erp.inventorymanagement.service;


import com.example.Erp.inventorymanagement.dto.SuppliersRequest;
import com.example.Erp.inventorymanagement.dto.SuppliersResponse;

import java.util.List;

public interface SuppliersService {

    SuppliersResponse createSupplier(SuppliersRequest request);

    SuppliersResponse getSupplier(Integer id);

    List<SuppliersResponse> getAllSuppliers();

    SuppliersResponse updateSupplier(Integer id, SuppliersRequest request);

    void deleteSupplier(Integer id);

}