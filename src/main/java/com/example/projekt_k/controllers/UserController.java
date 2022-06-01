package com.example.projekt_k.controllers;

import com.example.projekt_k.dto.SearchRequest;
import com.example.projekt_k.dto.UserDto;
import com.example.projekt_k.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userPage")
    public String userPage(Model model) {
        UserDto currentUser = userService.getCurrentUser();
        model.addAttribute("user", currentUser);
        return "userInfo";
    }

    @PostMapping("/searchUser")
    public String userPageWithUser(@ModelAttribute("searchObject") SearchRequest searchRequest, Model model) {
        UserDto userByEmail = userService.findUserByEmail(searchRequest);
        model.addAttribute("user", userByEmail);
        return "userInfo";
    }

    @GetMapping("/users")
    public String usersPage(Model model) {
        List<UserDto> users = userService.getAll();
        model.addAttribute("users", users);
        return "users";
    }


    @GetMapping("/user/{id}")
    public String usersPage(@PathVariable Long id, Model model) {
        UserDto user = userService.getById(id);
        model.addAttribute("user", user);
        return "userInfo";
    }

    @GetMapping("/user")
    public String usersPage1(@RequestParam(name = "id", required = false) Long id,
                             @RequestParam(name = "name") String name,
                             Model model) {
        UserDto user = userService.getById(id);
        model.addAttribute("user", user);
        return "userInfo";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}
