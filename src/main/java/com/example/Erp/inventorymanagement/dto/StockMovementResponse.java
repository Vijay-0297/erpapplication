package com.example.Erp.inventorymanagement.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockMovementResponse {

    private Integer movementId;

    private Integer productId;

    private String movementType;

    private Integer quantity;

    private Integer referenceId;

    private String notes;

    private OffsetDateTime createdAt;
}