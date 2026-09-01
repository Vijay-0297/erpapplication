package com.example.Erp.inventorymanagement.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesResponse {

    private Integer saleId;

    private Long customerId;

    private String invoiceNumber;

    private OffsetDateTime saleDate;

    private BigDecimal totalAmount;

    private String paymentStatus;

    private Integer createdBy;
}