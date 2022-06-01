package com.example.projekt_k.services;

import com.example.projekt_k.dto.UserDto;
import com.example.projekt_k.entity.User;
import com.example.projekt_k.exeptions.UserNotFoundException;
import com.example.projekt_k.mappers.UserMapper;
import com.example.projekt_k.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AbstractService {

    private final UserRepository userRepository;

    public AbstractService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    protected UserDto getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        User user = userRepository.findByEmail(login);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("User not found");
        }
        return UserMapper.toDto(user);
    }
}