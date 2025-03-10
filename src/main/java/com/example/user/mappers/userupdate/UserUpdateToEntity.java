package com.example.user.mappers.userupdate;

import com.example.user.domain.UserUpdate;
import com.example.user.entity.UserEntity;
import com.example.user.mappers.ToEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateToEntity implements ToEntityMapper<UserEntity, UserUpdate> {
    @Override
    public UserEntity toEntity(UserUpdate domain) {
        if(domain == null) return null;

        return UserEntity
                .builder()
                .name(domain.getName())
                .username(domain.getUsername())
                .password(domain.getPassword())
                .role(domain.getRole())
                .build();
    }
}
