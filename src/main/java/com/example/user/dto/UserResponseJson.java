package com.example.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseJson {
    private long id;
    private String name;
    private String username;
    private String password;
    private String role;
}
