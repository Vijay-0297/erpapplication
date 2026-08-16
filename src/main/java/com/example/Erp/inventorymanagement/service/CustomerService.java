package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.CustomerRequest;
import com.example.Erp.inventorymanagement.dto.CustomerResponse;
import com.example.Erp.inventorymanagement.dto.CustomerUpdateRequest;


import java.util.List;

public interface CustomerService {

    CustomerResponse create(CustomerRequest request);

    CustomerResponse update(Integer id, CustomerUpdateRequest request);

    CustomerResponse getById(Integer id);

    List<CustomerResponse> getAll();

    void delete(Integer id);
}