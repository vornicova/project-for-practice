package com.practice.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.Data;

@Data
public class UserDto {

    @Null(message = "Must be null when creating a new user")
    private Long id;

    @NotBlank(message = "First name must not be blank")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    private Boolean isActive;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email must not be blank")
    private String email;

}
