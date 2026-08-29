package com.example.Erp.inventorymanagement.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String password;

    @Pattern(
            regexp = "^[0-9]{10,15}$",
            message = "Mobile number must contain only 10 to 15 digits"
    )
    @NotBlank(message = "Mobile number is required")
    private String mobile;

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    @Pattern(
            regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$",
            message = "Email must contain only lowercase letters"
    )
    private String email;

    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    private String fullName;

    @NotNull(message = "Role ID is required")
    private Integer roleId;

    private Integer userId;

    private String status;

}