package com.example.Erp.inventorymanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

    @Entity
    @Table(name = "suppliers")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class Supplier {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "supplier_id")
        private Integer supplierId;

        @Column(name = "supplier_name", nullable = false)
        private String supplierName;

        @Column(name = "contact_person")
        private String contactPerson;

        private String phone;

        private String email;

        private String address;

        private String status;

        @Column(name = "created_at", insertable = false, updatable = false)
        private OffsetDateTime createdAt;
    }

