package com.example.user.mappers.userresponse;

import com.example.user.domain.UserResponse;
import com.example.user.entity.UserEntity;
import com.example.user.mappers.FromEntityMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserResponseFromEntity implements FromEntityMapper<UserResponse, UserEntity> {
    @Override
    public UserResponse fromEntity(UserEntity entity) {
        if(entity == null) return null;

        return UserResponse
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .role(entity.getRole())
                .build();
    }

    @Override
    public List<UserResponse> fromEntity(List<UserEntity> entities) {
        return Optional.ofNullable(entities)
                .map(list -> list.stream()
                        .map(this::fromEntity)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
}
