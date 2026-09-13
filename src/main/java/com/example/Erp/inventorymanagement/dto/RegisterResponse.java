package com.example.Erp.inventorymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {

    private Integer userId;

    private String username;

    private String email;

    private String fullName;

    private String mobile;

    private Integer roleId;

    private String role;

    private String status;

    private String message;
}