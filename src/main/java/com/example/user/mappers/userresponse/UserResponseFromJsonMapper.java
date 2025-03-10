package com.example.user.mappers.userresponse;

import com.example.user.domain.UserResponse;
import com.example.user.domain.UserSignIn;
import com.example.user.dto.UserResponseJson;
import com.example.user.dto.UserSignInJson;
import org.springframework.stereotype.Component;
import com.example.user.mappers.ToDomainMapper;


@Component
public class UserResponseFromJsonMapper implements ToDomainMapper<UserResponseJson, UserResponse>{

    @Override
    public UserResponse toDomain(UserResponseJson json) {
        if(json == null) return null;

        return UserResponse
                .builder()
                .name(json.getName())
                .username(json.getUsername())
                .password(json.getPassword())
                .role(json.getRole())
                .build();
    }
}
