package com.example.Erp.inventorymanagement.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseReturnResponse {

    private Integer purchaseReturnId;

    private Integer purchaseId;

    private Integer supplierId;

    private OffsetDateTime returnDate;

    private BigDecimal totalAmount;

    private String notes;
}