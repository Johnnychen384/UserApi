package com.example.user.mappers.userupdate;

import com.example.user.domain.UserUpdate;
import com.example.user.dto.UserUpdateJson;
import com.example.user.mappers.ToDomainMapper;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateToDomainMapper implements ToDomainMapper<UserUpdateJson, UserUpdate> {
    @Override
    public UserUpdate toDomain(UserUpdateJson json) {
        if(json == null) return null;

        return UserUpdate
                .builder()
                .id(json.getId())
                .name(json.getName())
                .username(json.getUsername())
                .password(json.getPassword())
                .role(json.getRole())
                .build();
    }
}
