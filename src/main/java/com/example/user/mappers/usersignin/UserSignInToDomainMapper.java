package com.example.user.mappers.usersignin;

import com.example.user.domain.UserSignIn;
import com.example.user.dto.UserSignInJson;
import com.example.user.mappers.ToDomainMapper;
import org.springframework.stereotype.Component;

@Component
public class UserSignInToDomainMapper implements ToDomainMapper<UserSignInJson, UserSignIn> {
    public UserSignInToDomainMapper(){}

    @Override
    public UserSignIn toDomain(UserSignInJson json) {
        if(json == null) return null;

        return UserSignIn
                .builder()
                .username(json.getUsername())
                .password(json.getPassword())
                .build();
    }
}
