package com.example.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateJson {
    @NotBlank(message = "ID cannot be empty.")
    private long id;
    private String name;
    private String username;
    private String password;
    private String role;
}
