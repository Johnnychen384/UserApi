package com.example.user.mappers.userregistration;

import com.example.user.domain.UserRegistration;
import com.example.user.dto.UserRegistrationJson;
import com.example.user.mappers.ToDomainMapper;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationToDomainMapper implements ToDomainMapper<UserRegistrationJson, UserRegistration> {

    public UserRegistrationToDomainMapper(){}

    @Override
    public UserRegistration toDomain(UserRegistrationJson json) {
        if(json == null) return null;

        return UserRegistration
                .builder()
                .name(json.getName())
                .username(json.getUsername())
                .password(json.getPassword())
                .build();
    }
}
