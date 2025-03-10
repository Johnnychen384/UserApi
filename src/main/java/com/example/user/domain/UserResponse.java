package com.example.user.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class UserResponse {
    private long id;
    private String name;
    private String username;
    private String password;
    private String role;
}
