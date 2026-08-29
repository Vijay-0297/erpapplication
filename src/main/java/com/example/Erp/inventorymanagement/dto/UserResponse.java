package com.example.Erp.inventorymanagement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {

    private Integer userId;

    private String username;

    private String mobile;

    private String email;

    private String fullName;

    private String role;

    private String status;
}