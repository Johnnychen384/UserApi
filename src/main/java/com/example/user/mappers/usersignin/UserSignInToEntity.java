package com.example.user.mappers.usersignin;

import com.example.user.domain.UserSignIn;
import com.example.user.entity.UserEntity;
import com.example.user.mappers.ToEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class UserSignInToEntity implements ToEntityMapper<UserEntity, UserSignIn> {
    @Override
    public UserEntity toEntity(UserSignIn domain) {
        if(domain == null) return null;

        return UserEntity
                .builder()
                .username(domain.getUsername())
                .password(domain.getPassword())
                .build();
    }
}
