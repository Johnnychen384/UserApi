package com.example.user.mappers;

import java.util.List;

public interface FromEntityMapper <D, E>{
    D fromEntity(E entity);
    List<D> fromEntity(List<E> entities);
}
