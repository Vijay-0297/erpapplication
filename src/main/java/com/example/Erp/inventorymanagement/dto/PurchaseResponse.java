package com.example.Erp.inventorymanagement.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseResponse {

    private Integer purchaseId;

    private Integer supplierId;

    private String supplierName;

    private String invoiceNumber;

    private LocalDateTime purchaseDateTime;

    private BigDecimal totalAmount;

    private String paymentStatus;

    private Integer createdBy;
}