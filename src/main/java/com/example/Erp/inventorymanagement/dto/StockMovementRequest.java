package com.example.Erp.inventorymanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockMovementRequest {

    @NotNull(message = "Product ID is required")
    private Integer productId;

    @NotBlank(message = "Movement type is required")
    @Size(max = 50, message = "Movement type must not exceed 50 characters")
    private String movementType;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;

    private Integer referenceId;

    private String notes;
}