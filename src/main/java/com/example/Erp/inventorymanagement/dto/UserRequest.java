package com.example.Erp.inventorymanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private String username;

    private String password;

    private String email;

    private String fullName;

    private Integer roleId;

    private Integer userId;
}