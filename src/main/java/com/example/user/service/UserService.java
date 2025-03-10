package com.example.user.service;

import com.example.user.domain.UserRegistration;
import com.example.user.domain.UserResponse;
import com.example.user.domain.UserSignIn;

import java.util.List;

public interface UserService {
    UserResponse register(UserRegistration userRegistration);

    UserResponse login(UserSignIn userSignIn);

    UserResponse find(String username);

    List<UserResponse> findAll();

    void update(UserResponse user);
}
