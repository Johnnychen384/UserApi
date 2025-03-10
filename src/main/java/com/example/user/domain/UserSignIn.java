package com.example.user.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSignIn {
    private String username;
    private String password;
}
