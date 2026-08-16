package com.example.Erp.inventorymanagement.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuppliersResponse {

    private Integer supplierId;
    private String supplierName;
    private String contactPerson;
    private String phone;
    private String email;
    private String address;
    private String status;
    private OffsetDateTime createdAt;

}