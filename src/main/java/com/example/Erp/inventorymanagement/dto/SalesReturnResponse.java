
        package com.example.Erp.inventorymanagement.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesReturnResponse {

    private Integer salesReturnId;

    private Integer saleId;

    private Integer customerId;

    private OffsetDateTime returnDate;

    private BigDecimal totalAmount;

    private String refundStatus;

    private String notes;
}

