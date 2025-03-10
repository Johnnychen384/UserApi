package com.example.user.mappers;

import java.util.List;

public interface ToJsonMapper <D, J>{
    J toJson(D domain);
    List<J> toJsonList(List<D> domains);
}
