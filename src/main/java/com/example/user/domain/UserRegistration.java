package com.example.user.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistration {
    private String name;
    private String username;
    private String password;
}
