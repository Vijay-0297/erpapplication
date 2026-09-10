
        package com.example.Erp.inventorymanagement.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesReturnRequest {

    @NotNull(message = "Sale ID is required")
    private Integer saleId;

    @NotNull(message = "Customer ID is required")
    private Integer customerId;

    @NotNull(message = "Total amount is required")
    @DecimalMin(
            value = "0.01",
            message = "Total amount must be greater than 0"
    )
    private BigDecimal totalAmount;

    @Size(
            max = 20,
            message = "Refund status cannot exceed 20 characters"
    )
    private String refundStatus;

    private String notes;
}

