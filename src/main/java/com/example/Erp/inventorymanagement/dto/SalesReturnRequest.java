package com.example.Erp.inventorymanagement.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SalesReturnRequest {

    @NotNull(message = "Sale ID is required")
    private Integer saleId;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @DecimalMin(value = "0.00", message = "Total amount cannot be negative")
    @Digits(integer = 10, fraction = 2, message = "Total amount must have maximum 2 decimal places")
    private BigDecimal totalAmount;

    @Size(max = 20, message = "Refund status cannot exceed 20 characters")
    private String refundStatus;

    private String notes;
}