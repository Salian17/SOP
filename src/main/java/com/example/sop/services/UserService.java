package com.example.sop.services;

import com.example.sop.dtos.UserDto;

import java.util.Optional;

public interface UserService {
    Iterable<UserDto> getAll();
    Optional<UserDto> getById(Long id);
    UserDto registry(UserDto user);
    UserDto update(UserDto user);
    void delete(Long id);
}
