package com.example.Erp.inventorymanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdate {

    @NotNull(message = "User ID is required")
    private Integer userId;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50,
            message = "Username must be between 3 and 50 characters")
//    @Pattern(
//            regexp = "^[a-z0-9._-]+$",
//            message = "Username must contain only lowercase letters, numbers, dot, underscore or hyphen"
//    )
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 100,
            message = "Full name must be between 2 and 100 characters")
    private String fullName;

    @NotBlank(message = "Mobile number is required")
    @Pattern(
            regexp = "^[0-9]{10,15}$",
            message = "Mobile number must contain only 10 to 15 digits"
    )
    private String mobile;

    @NotNull(message = "Role ID is required")
    private Integer roleId;

    private String password;

    private String status;
}