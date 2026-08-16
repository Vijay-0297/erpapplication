package com.example.Erp.inventorymanagement.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseItemResponse {

    private Integer purchaseItemId;

    private Integer purchaseId;

    private Integer productId;

    private Integer quantity;

    private BigDecimal purchasePrice;

    private BigDecimal total;
}