package com.example.projekt_k.services;

import com.example.projekt_k.dto.SearchRequest;
import com.example.projekt_k.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto findUserByEmail(SearchRequest searchRequest);

    UserDto createUser(UserDto userDto);

    List<UserDto> getAll();

    UserDto getById(Long id);

    void deleteUserById(Long id);

    UserDto getCurrentUser();

    List<UserDto> searchUsers(UserFilter userFilter);

    void activateUser(String code);
}
