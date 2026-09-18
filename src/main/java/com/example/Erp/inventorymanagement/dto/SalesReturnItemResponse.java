package com.example.Erp.inventorymanagement.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesReturnItemResponse {

    private Integer salesReturnItemId;

    private Integer salesReturnId;

    private Integer productId;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal total;
}