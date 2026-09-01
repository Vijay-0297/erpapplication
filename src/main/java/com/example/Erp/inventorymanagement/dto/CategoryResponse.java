package com.example.Erp.inventorymanagement.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class CategoryResponse {

    private Integer categoryId;
    private String categoryName;
    private String description;
    private String status;
    private OffsetDateTime createdAt;
}