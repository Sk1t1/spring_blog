package com.example.projekt_k.controllers;

import com.example.projekt_k.dto.UserDto;
import com.example.projekt_k.services.UserFilter;
import com.example.projekt_k.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/search")
    public ResponseEntity<List<UserDto>> searchUsers(@RequestBody(required = false) UserFilter userFilter) {
        return new ResponseEntity<>(userService.searchUsers(userFilter), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @PostMapping("/activateUser")
    public void activateUser(@RequestBody String code) {
        userService.activateUser(code);
    }
}