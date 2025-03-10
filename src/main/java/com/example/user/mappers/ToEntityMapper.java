package com.example.user.mappers;

public interface ToEntityMapper <E, D>{
    E toEntity(D domain);
}
