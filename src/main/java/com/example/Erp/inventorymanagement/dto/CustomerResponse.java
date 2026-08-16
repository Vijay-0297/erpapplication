package com.example.Erp.inventorymanagement.dto;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class CustomerResponse {

    private Integer customerId;

    private String customerName;

    private String phone;

    private String email;

    private String address;

    private String status;

    private OffsetDateTime createdAt;
}