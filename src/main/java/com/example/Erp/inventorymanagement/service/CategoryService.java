package com.example.Erp.inventorymanagement.service;


import com.example.Erp.inventorymanagement.dto.CategoryRequest;
import com.example.Erp.inventorymanagement.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse create(CategoryRequest request);

    List<CategoryResponse> getAll();

    CategoryResponse getById(Integer id);

    CategoryResponse update(Integer id, CategoryRequest request);

    void delete(Integer id);
}
