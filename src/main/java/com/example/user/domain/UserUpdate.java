package com.example.user.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdate {
    private long id;
    private String name;
    private String username;
    private String password;
    private String role;
}
