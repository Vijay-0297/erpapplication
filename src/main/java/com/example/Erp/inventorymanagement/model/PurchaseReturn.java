package com.example.Erp.inventorymanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "purchase_returns")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_return_id")
    private Integer purchaseReturnId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @Column(name = "return_date")
    private OffsetDateTime returnDate;

    @Column(
            name = "total_amount",
            precision = 12,
            scale = 2,
            nullable = false
    )
    private BigDecimal totalAmount;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @PrePersist
    protected void onCreate() {
        if (returnDate == null) {
            returnDate = OffsetDateTime.now();
        }

        if (totalAmount == null) {
            totalAmount = BigDecimal.ZERO;
        }
    }
}