package com.example.Erp.inventorymanagement.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseRequest {

    private Integer supplierId;

    private String invoiceNumber;

    private LocalDateTime purchaseDateTime;

    private BigDecimal totalAmount;

    private String paymentStatus;

    private Integer createdBy;


}
