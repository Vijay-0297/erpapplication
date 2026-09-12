package com.example.Erp.inventorymanagement.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesItemResponse {

    private Integer saleItemId;

    private Integer saleId;

    private Integer productId;

    private Integer quantity;

    private BigDecimal sellingPrice;

    private BigDecimal total;
}