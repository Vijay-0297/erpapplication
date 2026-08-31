package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.CustomerRequest;
import com.example.Erp.inventorymanagement.dto.CustomerResponse;
import com.example.Erp.inventorymanagement.dto.CustomerUpdateRequest;


import java.util.List;

public interface CustomerService {

    CustomerResponse create(CustomerRequest request);

    CustomerResponse update(Long id, CustomerUpdateRequest request);

    CustomerResponse getById(Long id);

    List<CustomerResponse> getAll();

    void delete(Long id);
}