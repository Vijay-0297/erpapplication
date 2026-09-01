package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.ProductRequest;
import com.example.Erp.inventorymanagement.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse create(ProductRequest request);

    ProductResponse update(Integer id, ProductRequest request);

    ProductResponse getById(Integer id);

    List<ProductResponse> getAll();

    void delete(Integer id);

}
