package com.example.Erp.inventorymanagement.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesRequest {

    private Integer customerId;

    private String invoiceNumber;

    private BigDecimal totalAmount;

    private String paymentStatus;

    private Integer createdBy;
}