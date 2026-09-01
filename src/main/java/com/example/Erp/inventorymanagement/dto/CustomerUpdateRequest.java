package com.example.Erp.inventorymanagement.dto;

import lombok.Data;

@Data
public class CustomerUpdateRequest {

    private String customerName;
    private String phone;
    private String email;
    private String address;
    private String status;
}
