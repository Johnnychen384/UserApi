package com.example.user.mappers;

public interface ToDomainMapper<J, D>{
    D toDomain(J json);
}
