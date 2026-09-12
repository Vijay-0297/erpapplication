package com.example.Erp.inventorymanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SalesItemRequest {

    @NotNull(message = "Sale ID is required")
    private Integer saleId;

    @NotNull(message = "Product ID is required")
    private Integer productId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "Selling price is required")
    @Positive(message = "Selling price must be greater than 0")
    private BigDecimal sellingPrice;
}