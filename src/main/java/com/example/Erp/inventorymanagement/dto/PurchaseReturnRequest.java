package com.example.Erp.inventorymanagement.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PurchaseReturnRequest {

    @NotNull(message = "Purchase ID is required")
    private Integer purchaseId;

    @NotNull(message = "Supplier ID is required")
    private Integer supplierId;

    @DecimalMin(
            value = "0.00",
            message = "Total amount cannot be negative"
    )
    private BigDecimal totalAmount;

    private String notes;
}