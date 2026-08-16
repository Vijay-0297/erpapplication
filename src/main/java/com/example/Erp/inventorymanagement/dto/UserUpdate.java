package com.example.Erp.inventorymanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdate {

    private String email;

    private String fullName;

    private Integer roleId;

    private String status;
}
