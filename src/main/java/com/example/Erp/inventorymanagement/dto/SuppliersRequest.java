package com.example.Erp.inventorymanagement.dto;

import lombok.*;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class SuppliersRequest {

        private Integer supplierId;
        private String supplierName;
        private String contactPerson;
        private String phone;
        private String email;
        private String address;
        private String status;

    }


