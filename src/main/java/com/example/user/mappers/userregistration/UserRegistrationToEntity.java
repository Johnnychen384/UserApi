package com.example.user.mappers.userregistration;

import com.example.user.domain.UserRegistration;
import com.example.user.entity.UserEntity;
import com.example.user.mappers.ToEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationToEntity implements ToEntityMapper<UserEntity, UserRegistration> {
    @Override
    public UserEntity toEntity(UserRegistration domain) {
        if(domain == null) return null;

        return UserEntity
                .builder()
                .name(domain.getName())
                .username(domain.getUsername())
                .password(domain.getPassword())
                .build();
    }
}
