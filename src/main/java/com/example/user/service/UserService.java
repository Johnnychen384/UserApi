package com.example.user.service;

import com.example.user.domain.UserRegistration;
import com.example.user.domain.UserResponse;
import com.example.user.domain.UserSignIn;
import com.example.user.dto.UserRegistrationJson;

public interface UserService {
    UserResponse register (UserRegistration userRegistration);
    UserResponse login(UserSignIn userSignIn);
}
