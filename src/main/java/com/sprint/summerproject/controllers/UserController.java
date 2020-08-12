package com.sprint.summerproject.controllers;

import com.sprint.summerproject.models.User;
import com.sprint.summerproject.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users/all")
    public List<User> getAll() {
        return userRepository.findAll();
    }

}
