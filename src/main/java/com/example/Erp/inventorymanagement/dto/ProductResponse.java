package com.example.Erp.inventorymanagement.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Integer productId;

    private Integer categoryId;

    private String productName;

    private String sku;

    private String barcode;

    private BigDecimal purchasePrice;

    private BigDecimal sellingPrice;

    private Integer stockQuantity;

    private Integer minimumStock;

    private String unit;

    private String status;

    private OffsetDateTime createdAt;
}
