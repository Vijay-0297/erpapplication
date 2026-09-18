package com.example.Erp.inventorymanagement.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseReturnItemResponse {

    private Integer purchaseReturnItemId;

    private Integer purchaseReturnId;

    private Integer productId;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal total;
}