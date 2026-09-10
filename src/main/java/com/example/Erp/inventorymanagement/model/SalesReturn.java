
        package com.example.Erp.inventorymanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "sales_returns")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_return_id")
    private Integer salesReturnId;

    @Column(name = "sale_id")
    private Integer saleId;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "return_date")
    private OffsetDateTime returnDate;

    @Column(name = "total_amount", precision = 12, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "refund_status", length = 20)
    private String refundStatus;

    @Column(name = "notes")
    private String notes;

    @PrePersist
    protected void onCreate() {
        if (returnDate == null) {
            returnDate = OffsetDateTime.now();
        }

        if (refundStatus == null || refundStatus.isBlank()) {
            refundStatus = "PENDING";
        }
    }
}

