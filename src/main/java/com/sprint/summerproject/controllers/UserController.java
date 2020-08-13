package com.sprint.summerproject.controllers;

import com.sprint.summerproject.exception.UserExistException;
import com.sprint.summerproject.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/tel")
    public String addUserByTel(@RequestParam String tel, @RequestParam String password) {
        try {
            userService.createUserByTel(tel, password);
            return "Yes";
        } catch (UserExistException e) {
            return "No";
        }
    }

    @PostMapping("/user/email")
    public String addUserByEmail(@RequestParam String email, @RequestParam String password) {
        try {
            userService.createUserByEmail(email, password);
            return "Yes";
        } catch (UserExistException e) {
            return "No";
        }
    }

}
