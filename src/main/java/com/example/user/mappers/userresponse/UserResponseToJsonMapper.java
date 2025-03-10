package com.example.user.mappers.userresponse;

import com.example.user.domain.UserResponse;
import com.example.user.dto.UserResponseJson;
import com.example.user.mappers.ToJsonMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserResponseToJsonMapper implements ToJsonMapper<UserResponse, UserResponseJson> {
    @Override
    public UserResponseJson toJson(UserResponse domain) {
        if(domain == null) return null;

        UserResponseJson json = new UserResponseJson();
        json.setId(domain.getId());
        json.setName(domain.getName());
        json.setUsername(domain.getUsername());
        json.setPassword(domain.getPassword());
        json.setRole(domain.getRole());
        return json;
    }

    @Override
    public List<UserResponseJson> toJsonList(List<UserResponse> domains) {
        return Optional.ofNullable(domains)
                .map(list -> list.stream()
                        .map(this::toJson)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
}
